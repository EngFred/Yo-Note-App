package com.omongole.fred.notesapp.ui.screens.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omongole.fred.notesapp.data.modal.Note
import com.omongole.fred.notesapp.domain.useCases.GetNoteByIdUseCase
import com.omongole.fred.notesapp.domain.useCases.InsertNoteUseCase
import com.omongole.fred.notesapp.domain.useCases.UpdateNoteUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailScreenViewModel @AssistedInject constructor(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val insertNoteUseCase: InsertNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    @Assisted private val noteId: Long
) : ViewModel() {

    private val tag = DetailScreenViewModel::class.simpleName

    val noteState = mutableStateOf(DetailScreenState())
    var isValidData by mutableStateOf(false)

    init {
        initialize()
    }

    fun onEvent( event: DetailScreenEvent ) {
        when( event ) {
            is DetailScreenEvent.TitleChanged -> {
                noteState.value = noteState.value.copy(
                    title = event.title
                )
                printState()
            }
            is DetailScreenEvent.DescriptionChanged -> {
                noteState.value = noteState.value.copy(
                    description = event.description
                )
                printState()
            }
            is DetailScreenEvent.BookMarkChanged -> {
                noteState.value = noteState.value.copy(
                    isBookMarked = event.value
                )
                printState()
            }
            is DetailScreenEvent.SaveClicked -> {
                printState()
                val newNote = Note(
                    id = noteState.value.id,
                    title = noteState.value.title,
                    description = noteState.value.description,
                    createdDate = noteState.value.createdDate,
                    isBookMarked = noteState.value.isBookMarked
                )
                insertOrUpdateNote( newNote )
            }
        }
        isValidData = noteState.value.title.isNotEmpty() && noteState.value.description.isNotEmpty()
    }

    private fun initialize() {
        val isEditMode = noteId != -1L //old note
        noteState.value = noteState.value.copy( isEditMode = isEditMode )
        if ( isEditMode ) {
            getNote( noteId )
        }
    }

    private fun insertOrUpdateNote( note: Note ) {
        if ( !noteState.value.isEditMode ) {
            viewModelScope.launch( Dispatchers.IO ) {
                insertNoteUseCase(note)
            }
        } else {
            viewModelScope.launch {
                updateNoteUseCase(note)
            }
        }

    }

    private fun printState() {
        Log.d(tag, "${noteState.value}")
    }

    private fun getNote( noteId: Long ) {
        viewModelScope.launch( Dispatchers.Main ) {
            getNoteByIdUseCase(noteId).collectLatest {
                noteState.value = noteState.value.copy(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    isBookMarked = it.isBookMarked,
                    createdDate = it.createdDate
                )
            }
        }
    }

}