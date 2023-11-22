package com.omongole.fred.notesapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    modifier: Modifier = Modifier,
    placeHolderAlign: TextAlign? = null
) {
    OutlinedTextField(
        value = value,
        modifier = modifier,
        onValueChange = {
             onValueChange(it)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = placeHolder,
                textAlign = placeHolderAlign,
                modifier = modifier.fillMaxWidth()
            )
        },
    )
    
}