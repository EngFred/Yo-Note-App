package com.omongole.fred.notesapp.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
    private val noteId: Long,
    private val assistedFactory: DetailAssistedFactory
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(noteId) as T
    }
}

@AssistedFactory
interface DetailAssistedFactory{
    fun create( noteId: Long ) : DetailScreenViewModel
}