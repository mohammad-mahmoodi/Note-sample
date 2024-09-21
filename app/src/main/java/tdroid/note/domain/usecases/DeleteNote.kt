package tdroid.note.domain.usecases

import tdroid.note.domain.model.Note
import tdroid.note.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}