package tdroid.note.domain.usecases

import tdroid.note.domain.model.Note
import tdroid.note.domain.repository.NoteRepository


class GetNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}