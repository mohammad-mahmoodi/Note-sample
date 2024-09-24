package tdroid.note.domain.usecases

import org.junit.jupiter.api.Assertions.*

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import tdroid.note.domain.model.InvalidNoteException
import tdroid.note.domain.model.Note
import tdroid.note.domain.repository.NoteRepository

class AddNoteTest {

    private lateinit var repository: NoteRepository
    private lateinit var addNote: AddNote

    @Before
    fun setUp() {
        repository = mock(NoteRepository::class.java)
        addNote = AddNote(repository)
    }

    @Test(expected = InvalidNoteException::class)
    fun `invoke should throw InvalidNoteException when title is blank`() = runBlocking {
        val note = Note(
            id = 1,
            title = "",
            content = "Some content",
            color = 1,
            timeReminder = 0L,
            timestamp = 0L
        )
        addNote.invoke(note)
    }

    @Test(expected = InvalidNoteException::class)
    fun `invoke should throw InvalidNoteException when content is blank`() = runBlocking {
        val note = Note(
            id = 1,
            title = "Some title",
            content = "",
            color = 1,
            timeReminder = 0L,
            timestamp = 0L

        )
        addNote.invoke(note)
    }

    @Test
    fun `invoke should call insertNote when note is valid`() = runBlocking {
        val note = Note(
            id = 1,
            title = "Some title",
            content = "Some content",
            color = 1,
            timeReminder = 0L,
            timestamp = 0L
        )
        addNote.invoke(note)
        verify(repository).insertNote(note)
    }
}
