package com.omongole.fred.notesapp.ui.screens.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omongole.fred.notesapp.data.modal.Note
import com.omongole.fred.notesapp.domain.useCases.DeleteNoteUseCase
import com.omongole.fred.notesapp.domain.useCases.GetBookMarkedNotesUseCase
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
class BookMarkScreenViewModel @Inject constructor(
    private val getBookMarkedNotesUseCase: GetBookMarkedNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {

    private val _notes = MutableStateFlow<Resource<List<Note>>>(Resource.Loading)
    val notes = _notes.asStateFlow()

    init {
        getBookMarkedNotes()
    }

    private fun getBookMarkedNotes() {
        Resource.Loading
        viewModelScope.launch ( Dispatchers.IO ) {
            getBookMarkedNotesUseCase()
                .catch {
                    _notes.value = Resource.Failure("$it")
                }
                .collectLatest {
                    _notes.value = Resource.Success(it)
                }
        }
    }

    fun onEvent( event: BookMarkScreenEvent ) {
        when( event ) {
            is BookMarkScreenEvent.NoteDeleted -> {
                deleteNote( event.note )
            }
            is BookMarkScreenEvent.RemovedBookMark -> {
                removeBookMark( event.note.copy( isBookMarked = false ) )
            }
        }
    }

    private fun removeBookMark( note: Note ) {
        viewModelScope.launch {
            updateNoteUseCase( note )
        }
    }

    private fun deleteNote( note: Note ) {
        viewModelScope.launch( Dispatchers.IO ) {
            deleteNoteUseCase( note )
        }
    }

}