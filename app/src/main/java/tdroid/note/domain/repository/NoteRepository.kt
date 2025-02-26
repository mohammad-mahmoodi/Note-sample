package tdroid.note.domain.repository

import kotlinx.coroutines.flow.Flow
import tdroid.note.domain.model.Note

//we declared an interface for repository, so as to make testing easier
interface NoteRepository {

    fun getNotes() : Flow<List<Note>>

    suspend fun getNoteById(id:Int) : Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}