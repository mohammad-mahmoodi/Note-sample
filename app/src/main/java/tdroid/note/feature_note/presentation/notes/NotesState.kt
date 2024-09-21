package tdroid.note.feature_note.presentation.notes

import tdroid.note.feature_note.domain.model.Note
import tdroid.note.feature_note.domain.utils.NoteOrder
import tdroid.note.feature_note.domain.utils.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
