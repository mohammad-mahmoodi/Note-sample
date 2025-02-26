package tdroid.note.data.repository

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.coroutines.flow.Flow
import tdroid.note.data.data_source.NoteDao
import tdroid.note.service.worker.ReminderWorker
import tdroid.note.domain.model.Note
import tdroid.note.domain.repository.NoteRepository
import java.util.concurrent.TimeUnit

class NoteRepositoryImpl  constructor(
    private val dao: NoteDao,
    private val workManager: WorkManager
) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
    return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {

        if (note.timeReminder != 0L ) {

            val data = Data.Builder()
                .putInt("id" , note.id ?: -1)
                .build()

            val myWorkRequest = OneTimeWorkRequest.Builder(ReminderWorker::class.java)
                .setInitialDelay(note.timeReminder - System.currentTimeMillis()  , TimeUnit.MILLISECONDS)
                .setInputData(data)
                .build()

            workManager.enqueueUniqueWork("reminder" ,ExistingWorkPolicy.REPLACE, myWorkRequest)
        }

        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}
