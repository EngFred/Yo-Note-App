package com.omongole.fred.notesapp.ui.screens.detail

sealed class DetailScreenEvent {
    data class TitleChanged( val title: String ) : DetailScreenEvent()
    data class DescriptionChanged( val description: String ) : DetailScreenEvent()
    data class BookMarkChanged( val value: Boolean ) : DetailScreenEvent()
    object SaveClicked: DetailScreenEvent()
}
