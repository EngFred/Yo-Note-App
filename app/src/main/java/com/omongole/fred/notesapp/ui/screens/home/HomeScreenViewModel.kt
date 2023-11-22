package com.omongole.fred.notesapp.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omongole.fred.notesapp.data.modal.Note
import com.omongole.fred.notesapp.domain.useCases.DeleteNoteUseCase
import com.omongole.fred.notesapp.domain.useCases.GetAllNotesUseCase
import com.omongole.fred.notesapp.domain.useCases.UpdateNoteUseCase
import com.omongole.fred.notesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
): ViewModel() {

    private val tag = HomeScreenViewModel::class.simpleName

    private val _notes = MutableStateFlow<Resource<List<Note>>>(Resource.Loading)
    val notes = _notes.asStateFlow()

    init {
        getAllNotes()
    }

    fun onEvent( event: HomeScreenEvent ) {
        when( event ) {
            is HomeScreenEvent.BookMarkChanged -> {
                val updatedNote: Note =  event.note.copy( isBookMarked = !event.note.isBookMarked )
                bookMarkNote( updatedNote )
                Log.d(tag, "$updatedNote")
            }
            is HomeScreenEvent.NotedDeleted -> {
                deleteNote( event.note )
                Log.d(tag, "Note ${event.note.id} deleted!")
            }
        }
    }

    private fun bookMarkNote( note: Note ) {
        viewModelScope.launch ( Dispatchers.IO ) {
            updateNoteUseCase( note )
        }
    }

    private fun deleteNote( note: Note ) {
        viewModelScope.launch( Dispatchers.IO ) {
            deleteNoteUseCase( note )
        }
    }

    private fun getAllNotes() {
        Resource.Loading
        viewModelScope.launch ( Dispatchers.IO ){
            getAllNotesUseCase()
                .catch {
                    _notes.value = Resource.Failure("$it")
                }
                .collectLatest {
                _notes.value = Resource.Success(it)
            }
        }
    }
}