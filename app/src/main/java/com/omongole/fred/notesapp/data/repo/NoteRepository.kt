package com.omongole.fred.notesapp.data.repo

import com.omongole.fred.notesapp.data.modal.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getAllNotes() : Flow<List<Note>>
    fun getAllBookMarkedNotes() : Flow<List<Note>>
    fun getNoteById( id: Long ) : Flow<Note>
    suspend fun insertNote( note: Note )
    suspend fun deleteNote( note: Note )
    suspend fun updateNote( note: Note )
}