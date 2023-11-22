package com.omongole.fred.notesapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Failure(
    text: String
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = text, color = MaterialTheme.colorScheme.error )
    }
}

@Preview( showBackground = true )
@Composable
fun FailurePreview() {
    Failure(text = "Error message")
}