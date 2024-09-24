package tdroid.note.domain.usecases

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import tdroid.note.domain.model.Note
import tdroid.note.domain.repository.NoteRepository

class GetNoteTest {

    private lateinit var repository: NoteRepository
    private lateinit var getNote: GetNote

    @Before
    fun setUp() {
        repository = mock(NoteRepository::class.java)
        getNote = GetNote(repository)
    }

    @Test
    fun `invoke should return note when note exists`() = runBlocking {
        val note = Note(
            id = 1,
            title = "Some title",
            content = "Some content",
            timestamp = 123456789,
            color = 1,
            timeReminder = 0L
        )
        `when`(repository.getNoteById(1)).thenReturn(note)

        val result = getNote.invoke(1)

        assertNotNull(result)
        assertEquals(note, result)
    }

    @Test
    fun `invoke should return null when note does not exist`() = runBlocking {
        `when`(repository.getNoteById(1)).thenReturn(null)

        val result = getNote.invoke(1)

        assertNull(result)
    }
}
