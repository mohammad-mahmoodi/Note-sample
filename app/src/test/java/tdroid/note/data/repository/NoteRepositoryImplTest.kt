package tdroid.note.data.repository



import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.verify
import tdroid.note.data.data_source.NoteDao
import tdroid.note.domain.model.Note

class NoteRepositoryImplTest {

    private lateinit var dao: NoteDao
    private lateinit var workManager: WorkManager
    private lateinit var repository: NoteRepositoryImpl

    @Before
    fun setUp() {
        dao = mock(NoteDao::class.java)
        workManager = mock(WorkManager::class.java)
        repository = NoteRepositoryImpl(dao, workManager)
    }

    @Test
    fun `getNotes should return flow of notes`() = runBlocking {
        val notes = listOf(
            Note(id = 1, title = "Note 1", content = "Content 1", timestamp = 123456789, color = 1, timeReminder = 0L),
            Note(id = 2, title = "Note 2", content = "Content 2", timestamp = 987654321, color = 2, timeReminder = 0L)
        )
        `when`(dao.getNotes()).thenReturn(flowOf(notes))

        val result: Flow<List<Note>> = repository.getNotes()

        result.collect { noteList ->
            assertEquals(2, noteList.size)
            assertEquals("Note 1", noteList[0].title)
            assertEquals("Note 2", noteList[1].title)
        }
    }

    @Test
    fun `getNoteById should return note when note exists`() = runBlocking {
        val note = Note(
            id = 1,
            title = "Note 1",
            content = "Content 1",
            timestamp = 123456789,
            color = 1,
            timeReminder = 0L
        )
        `when`(dao.getNoteById(1)).thenReturn(note)

        val result = repository.getNoteById(1)

        assertNotNull(result)
        assertEquals(note, result)
    }

    @Test
    fun `getNoteById should return null when note does not exist`() = runBlocking {
        `when`(dao.getNoteById(1)).thenReturn(null)

        val result = repository.getNoteById(1)

        assertNull(result)
    }

    @Test
    fun `insertNote should insert note and schedule reminder when timeReminder is set`(): Unit = runBlocking {
        val note = Note(
            id = 1,
            title = "Note 1",
            content = "Content 1",
            timestamp = 123456789,
            color = 1,
            timeReminder = System.currentTimeMillis() + 1000L
        )

        repository.insertNote(note)

        verify(dao).insertNote(note)
        verify(workManager).enqueueUniqueWork(
            eq("reminder"),
            eq(ExistingWorkPolicy.REPLACE),
            any(OneTimeWorkRequest::class.java)
        )
    }

    @Test
    fun `insertNote should insert note without scheduling reminder when timeReminder is not set`(): Unit = runBlocking {
        val note = Note(
            id = 1,
            title = "Note 1",
            content = "Content 1",
            timestamp = 123456789,
            color = 1,
            timeReminder = 0L
        )

        repository.insertNote(note)

        verify(dao).insertNote(note)
        verify(workManager, never()).enqueueUniqueWork(
            eq("reminder"),
            eq(ExistingWorkPolicy.REPLACE),
            any(OneTimeWorkRequest::class.java)
        )
    }

    @Test
    fun `deleteNote should delete note`() = runBlocking {
        val note = Note(
            id = 1,
            title = "Note 1",
            content = "Content 1",
            timestamp = 123456789,
            color = 1,
            timeReminder = 0L
        )

        repository.deleteNote(note)

        verify(dao).deleteNote(note)
    }
}
