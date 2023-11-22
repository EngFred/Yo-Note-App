package com.omongole.fred.notesapp.domain.useCases

import com.omongole.fred.notesapp.data.modal.Note
import com.omongole.fred.notesapp.data.repo.NoteRepository
import javax.inject.Inject

class InsertNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
){
    suspend operator fun invoke( note: Note ) = noteRepository.insertNote(note)
}