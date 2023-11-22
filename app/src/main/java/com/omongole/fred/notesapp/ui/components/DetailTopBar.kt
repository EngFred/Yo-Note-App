package com.omongole.fred.notesapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.omongole.fred.notesapp.R

@Composable
fun DetailTopBar(
    modifier: Modifier = Modifier,
    title: String,
    label: String,
    isBookMarked: Boolean,
    navigateUp: () -> Unit,
    onBookMarkChange: (Boolean) -> Unit,
    onTitleChange: (String) -> Unit
) {

    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        
        IconButton(onClick = { navigateUp() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
        }
        NoteTextField(
            value = title,
            modifier = modifier.weight(1f),
            onValueChange = { onTitleChange(it) },
            placeHolderAlign = TextAlign.Center,
            placeHolder = label
        )
        IconButton(onClick = {
            onBookMarkChange( !isBookMarked ) }
        ) {
            if (isBookMarked)
                Icon(painter = painterResource(id = R.drawable.bookmark_fill), contentDescription = "")
            else
                Icon(painter = painterResource(id = R.drawable.bookmark_boarder), contentDescription = "")
        }
    }
}