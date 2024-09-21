package tdroid.note.feature_note.presentation.notes

import tdroid.note.feature_note.domain.model.Note
import tdroid.note.feature_note.domain.utils.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSection : NotesEvent()
}
