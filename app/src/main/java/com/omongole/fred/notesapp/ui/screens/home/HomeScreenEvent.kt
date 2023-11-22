package com.omongole.fred.notesapp.ui.screens.home

import com.omongole.fred.notesapp.data.modal.Note

sealed class HomeScreenEvent {
    data class BookMarkChanged( val note: Note ) : HomeScreenEvent()
    data class NotedDeleted( val note: Note ) : HomeScreenEvent()
}
