package com.omongole.fred.notesapp.domain.repository

import com.omongole.fred.notesapp.data.database.NoteDao
import com.omongole.fred.notesapp.data.modal.Note
import com.omongole.fred.notesapp.data.repo.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao
) : NoteRepository {

    override fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes()
    }

    override fun getAllBookMarkedNotes(): Flow<List<Note>> {
        return dao.getBookMarkedNotes()
    }

    override fun getNoteById(id: Long): Flow<Note> {
        return dao.getNoteById( id )
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote( note )
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote( note )
    }

    override suspend fun updateNote(note: Note) {
        dao.updateNote( note )
    }

}