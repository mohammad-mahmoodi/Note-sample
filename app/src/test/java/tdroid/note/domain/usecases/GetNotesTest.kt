package tdroid.note.domain.usecases


import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import tdroid.note.domain.model.Note
import tdroid.note.domain.repository.NoteRepository
import tdroid.note.domain.utils.NoteOrder
import tdroid.note.domain.utils.OrderType

class GetNotesTest {

    private lateinit var repository: NoteRepository
    private lateinit var getNotes: GetNotes

    @Before
    fun setUp() {
        repository = mock(NoteRepository::class.java)
        getNotes = GetNotes(repository)
    }

    @Test
    fun `invoke should return notes sorted by title ascending`() = runBlocking {
        val notes = listOf(
            Note(id = 1, title = "B", content = "Content B", timestamp = 2, color = 1, timeReminder = 0L,),
            Note(id = 2, title = "A", content = "Content A", timestamp = 1, color = 2, timeReminder = 0L)
        )
        `when`(repository.getNotes()).thenReturn(flowOf(notes))

        val result = getNotes(NoteOrder.Title(OrderType.Ascending)).toList().flatten()

        assertEquals("A", result[0].title)
        assertEquals("B", result[1].title)
    }

    @Test
    fun `invoke should return notes sorted by date descending`() = runBlocking {
        val notes = listOf(
            Note(id = 1, title = "A", content = "Content A", timestamp = 1, color = 1, timeReminder = 0L),
            Note(id = 2, title = "B", content = "Content B", timestamp = 2, color = 2, timeReminder = 0L)
        )
        `when`(repository.getNotes()).thenReturn(flowOf(notes))

        val result = getNotes(NoteOrder.Date(OrderType.Descending)).toList().flatten()

        assertEquals(2, result[0].timestamp)
        assertEquals(1, result[1].timestamp)
    }

    @Test
    fun `invoke should return notes sorted by color descending`() = runBlocking {
        val notes = listOf(
            Note(id = 1, title = "A", content = "Content A", timestamp = 1, color = 1, timeReminder = 0L),
            Note(id = 2, title = "B", content = "Content B", timestamp = 2, color = 2, timeReminder = 0L)
        )
        `when`(repository.getNotes()).thenReturn(flowOf(notes))

        val result = getNotes(NoteOrder.Color(OrderType.Descending)).toList().flatten()

        assertEquals(2, result[0].color)
        assertEquals(1, result[1].color)
    }
}
