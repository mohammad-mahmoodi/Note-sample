package tdroid.note.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tdroid.note.data.data_source.NoteDB
import tdroid.note.data.repository.NoteRepositoryImpl
import tdroid.note.domain.repository.NoteRepository
import tdroid.note.domain.usecases.AddNote
import tdroid.note.domain.usecases.DeleteNote
import tdroid.note.domain.usecases.GetNote
import tdroid.note.domain.usecases.GetNotes
import tdroid.note.domain.usecases.NoteUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDB {
        return Room.databaseBuilder(
            app,
            NoteDB::class.java,
            NoteDB.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDB , app: Application): NoteRepository {
        return NoteRepositoryImpl(db.noteDao ,app.baseContext )
    }

//    @Singleton
//    @Provides
//    fun provideWidgetDao(db: NoteDB) = db.noteDao
//

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getIndividualNote = GetNote(repository)
        )
    }

}