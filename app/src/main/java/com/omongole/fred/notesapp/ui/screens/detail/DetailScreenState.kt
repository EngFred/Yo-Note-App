package com.omongole.fred.notesapp.ui.screens.detail

import java.util.Date

data class DetailScreenState(
    val id: Long = 0,
    val title: String = "",
    val description: String = "",
    val createdDate: Date = Date(),
    val isBookMarked: Boolean = false,
    val isEditMode: Boolean = false
)
