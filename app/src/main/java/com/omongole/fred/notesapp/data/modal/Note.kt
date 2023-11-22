package com.omongole.fred.notesapp.data.modal

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Note(
    @PrimaryKey( autoGenerate = true )
    val id: Long = 0,
    val title: String,
    val description: String,
    val createdDate: Date,
    val isBookMarked: Boolean = false
)
