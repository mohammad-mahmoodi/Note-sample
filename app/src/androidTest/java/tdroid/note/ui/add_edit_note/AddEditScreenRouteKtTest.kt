package tdroid.note.ui.add_edit_note


import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddEditScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalPermissionsApi::class)
    @Test
    fun testAddEditScreen_initialState() {
        composeTestRule.setContent {
            AddEditScreen(
                noteColor = 1,
                noteTitle = NoteTextFieldState(text = "test", hint = "Title", isHintVisible = false),
                noteContent = NoteTextFieldState(text = "noteContent", hint = "Content", isHintVisible = true),
                onEvent = {},
                reminderDate = 0,
                notificationPermission = null
            )
        }

        composeTestRule.onNodeWithText("test").assertExists()
        composeTestRule.onNodeWithText("Content").assertExists()
        composeTestRule.onNodeWithText("reminder").assertExists()
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Test
    fun testAddEditScreen_saveNote() {
        var noteSaved = false

        composeTestRule.setContent {
            AddEditScreen(
                noteColor = 1,
                noteTitle = NoteTextFieldState(text = "test", hint = "Title", isHintVisible = false),
                noteContent = NoteTextFieldState(text = "test", hint = "Content", isHintVisible = false),
                onEvent = { event ->
                    if (event is AddEditNoteEvent.SaveNote) {
                        noteSaved = true
                    }
                },
                reminderDate = 0,
                notificationPermission = null
            )
        }

        composeTestRule.onNodeWithContentDescription("Save Note").performClick()
        assert(noteSaved)
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Test
    fun testAddEditScreen_changeColor() {
        composeTestRule.setContent {
            AddEditScreen(
                noteColor = 1,
                noteTitle = NoteTextFieldState(text = "test", hint = "Title", isHintVisible = false),
                noteContent = NoteTextFieldState(text = "test", hint = "Content", isHintVisible = false),
                onEvent = {},
                reminderDate = 0,
                notificationPermission = null
            )
        }

        composeTestRule.onAllNodesWithTag("colorBox")[0].performClick()
// Add assertions to verify the color change if needed
    }
}
