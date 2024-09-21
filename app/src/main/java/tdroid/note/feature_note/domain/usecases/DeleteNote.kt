package tdroid.note.feature_note.domain.usecases

import tdroid.note.feature_note.domain.model.Note
import tdroid.note.feature_note.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}