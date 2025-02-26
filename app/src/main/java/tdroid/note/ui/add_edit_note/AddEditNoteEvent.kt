package tdroid.note.ui.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {
    data class EnteredTitle(val newTitle: String) : AddEditNoteEvent()

    data class ChangeTitleFocus(val focusState: FocusState) : AddEditNoteEvent()

    data class EnteredContent(val newContent: String) : AddEditNoteEvent()

    data class EnteredReminderDate(val date: Long) : AddEditNoteEvent()

    data class ChangeContentFocus(val focusState: FocusState) : AddEditNoteEvent()

    data class ChangeColor(val color: Int) : AddEditNoteEvent()

    object SaveNote : AddEditNoteEvent()
}