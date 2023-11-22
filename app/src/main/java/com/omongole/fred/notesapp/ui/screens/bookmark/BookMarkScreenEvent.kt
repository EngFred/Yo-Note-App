package com.omongole.fred.notesapp.ui.screens.bookmark

import com.omongole.fred.notesapp.data.modal.Note

sealed class BookMarkScreenEvent {
    data class NoteDeleted( val note: Note ) : BookMarkScreenEvent()
    data class RemovedBookMark( val note: Note ) : BookMarkScreenEvent()
}
