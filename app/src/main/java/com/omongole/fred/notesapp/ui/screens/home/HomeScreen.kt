package com.omongole.fred.notesapp.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.omongole.fred.notesapp.data.modal.Note
import com.omongole.fred.notesapp.ui.components.EmptyComponent
import com.omongole.fred.notesapp.ui.components.Failure
import com.omongole.fred.notesapp.ui.components.Loader
import com.omongole.fred.notesapp.ui.components.NotesList
import com.omongole.fred.notesapp.ui.navigation.Route
import com.omongole.fred.notesapp.util.Resource

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navigateToDetailScreen: (Long) -> Unit
) {

    val notes = viewModel.notes.collectAsState()

    when( notes.value ) {
        is Resource.Loading -> {
            Loader()
        }
        is Resource.Success -> {
            val mNotes  = (notes.value as Resource.Success<List<Note>>).data
            if ( mNotes.isEmpty() ) {
                EmptyComponent(text = "No Notes Yet!")
            } else {
                NotesList(
                    notes = mNotes,
                    onNoteClick = {
                        navigateToDetailScreen(it)
                    },
                    onNoteDelete = {
                        viewModel.onEvent( HomeScreenEvent.NotedDeleted(it) )
                    },
                    onNoteBookMark = {
                        viewModel.onEvent( HomeScreenEvent.BookMarkChanged(it) )
                    }
                )
            }
        }
        is Resource.Failure -> {
            Failure( (notes.value as Resource.Failure).errorMessage )
        }
    }

}