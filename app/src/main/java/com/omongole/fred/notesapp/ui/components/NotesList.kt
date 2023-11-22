package com.omongole.fred.notesapp.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.omongole.fred.notesapp.data.modal.Note

@Composable
fun NotesList(
    notes : List<Note>,
    onNoteClick: (Long) -> Unit,
    onNoteDelete: (Note) -> Unit,
    onNoteBookMark: (Note) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp)
    ) {
        itemsIndexed( notes ) { index, item ->  
            NoteCard(
                index = index,
                note = item,
                onNoteClick = onNoteClick,
                onNoteDelete = onNoteDelete,
                onNoteBookMark = onNoteBookMark
            )
        }
    }
}
