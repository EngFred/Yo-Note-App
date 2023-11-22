package com.omongole.fred.notesapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.omongole.fred.notesapp.R
import com.omongole.fred.notesapp.data.modal.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCard(
    index: Int,
    note: Note,
    onNoteClick: (Long) -> Unit,
    onNoteDelete: (Note) -> Unit,
    onNoteBookMark: (Note) -> Unit
) {
    val isEven = index % 2 == 0
    val shape = when {
        isEven -> RoundedCornerShape(topStart = 50f, bottomEnd = 50f)
        else -> RoundedCornerShape(topEnd = 50f, bottomStart = 50f)
    }

    val paddingStart = if (isEven) 25.dp else 10.dp

    Card(
        onClick = { onNoteClick( note.id ) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = shape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = paddingStart, end = 10.dp),
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 10.dp),
                text = note.description,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onNoteDelete( note ) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
                IconButton(
                    onClick = { onNoteBookMark( note ) }
                ) {
                    if (note.isBookMarked)
                        Icon(painter = painterResource(id = R.drawable.bookmark_fill), contentDescription = "")
                    else Icon(painter = painterResource(id = R.drawable.bookmark_boarder), contentDescription = "")
                }
            }
        }
    }
}