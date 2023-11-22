package com.omongole.fred.notesapp.ui.screens.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.omongole.fred.notesapp.ui.components.DetailTopBar
import com.omongole.fred.notesapp.ui.components.NoteTextField

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    noteId: Long,
    assistedFactory: DetailAssistedFactory,
    navigateUp: () -> Unit
) {

    val viewModel =  viewModel(
        modelClass = DetailScreenViewModel::class.java,
        factory = DetailViewModelFactory(
            noteId, assistedFactory
        )
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        DetailTopBar(
            title = viewModel.noteState.value.title ,
            label = "Enter Title" ,
            navigateUp = { navigateUp() },
            isBookMarked = viewModel.noteState.value.isBookMarked,
            onBookMarkChange = {
                 viewModel.onEvent( DetailScreenEvent.BookMarkChanged(it) )
            },
            onTitleChange = {
                viewModel.onEvent( DetailScreenEvent.TitleChanged(it) )
            }
        )
        Spacer(modifier = Modifier.size(10.dp))
        AnimatedVisibility( viewModel.isValidData ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick =  {
                        viewModel.onEvent( DetailScreenEvent.SaveClicked )
                        navigateUp()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        NoteTextField(
            value = viewModel.noteState.value.description,
            modifier = modifier.weight(1f),
            onValueChange = {
                viewModel.onEvent(DetailScreenEvent.DescriptionChanged(it))
            },
            placeHolder = "Enter description"
        )
    }
}