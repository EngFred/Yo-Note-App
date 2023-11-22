package com.omongole.fred.notesapp.domain.useCases

import com.omongole.fred.notesapp.data.repo.NoteRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val noteRepository: NoteRepository
){
    operator fun invoke(id:Long) = noteRepository.getNoteById(id)
}