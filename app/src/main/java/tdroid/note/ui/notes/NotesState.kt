package tdroid.note.ui.notes

import tdroid.note.domain.model.Note
import tdroid.note.domain.utils.NoteOrder
import tdroid.note.domain.utils.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
