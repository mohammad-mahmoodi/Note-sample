package tdroid.note.domain.usecases

import org.junit.jupiter.api.Assertions.*

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.*
import tdroid.note.domain.model.Note
import tdroid.note.domain.repository.NoteRepository

class DeleteNoteTest {

    private val repository = mock(NoteRepository::class.java)
    private val deleteNote = DeleteNote(repository)

    @Test
    fun `invoke should call deleteNote on repository`() = runBlocking {
// Given
        val note = Note(
            id = 1,
            title = "Test Note",
            content = "This is a test note.",
            color = 1,
            timeReminder = 0L,
            timestamp = 0L
        )

// When
        deleteNote.invoke(note)

// Then
        verify(repository).deleteNote(note)
    }
}