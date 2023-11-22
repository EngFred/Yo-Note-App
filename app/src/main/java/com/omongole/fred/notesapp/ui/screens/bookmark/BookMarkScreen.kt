package com.omongole.fred.notesapp.ui.screens.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.omongole.fred.notesapp.R
import com.omongole.fred.notesapp.data.modal.Note
import com.omongole.fred.notesapp.ui.components.EmptyComponent
import com.omongole.fred.notesapp.ui.components.Failure
import com.omongole.fred.notesapp.ui.components.Loader
import com.omongole.fred.notesapp.util.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookMarkScreen(
    viewModel: BookMarkScreenViewModel = hiltViewModel()
) {
    val notes = viewModel.notes.collectAsState()

    when( notes.value ) {
        is Resource.Loading -> {
            Loader()
        }
        is Resource.Success -> {
            val mNotes  = (notes.value as Resource.Success<List<Note>>).data
            if ( mNotes.isEmpty() ) {
                EmptyComponent(text = "No BookMarks Yet!")
            } else {
                LazyColumn( modifier = Modifier.fillMaxSize() ) {
                    items( (notes.value as Resource.Success<List<Note>>).data ) {
                        Card(
                            onClick = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp)
                            ) {
                                Text(
                                    text = it.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1
                                )
                                Spacer(modifier = Modifier.size(4.dp))
                                Text(
                                    text = it.description,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Normal,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                            ) {
                                IconButton(
                                    onClick = { viewModel.onEvent(BookMarkScreenEvent.RemovedBookMark(it)) }
                                ) {
                                    Icon(painter = painterResource(id = R.drawable.bookmark_fill), contentDescription = "")
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(
                                    onClick = { viewModel.onEvent(BookMarkScreenEvent.NoteDeleted(it)) }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        is Resource.Failure -> {
            Failure( (notes.value as Resource.Failure).errorMessage )
        }
    }

}