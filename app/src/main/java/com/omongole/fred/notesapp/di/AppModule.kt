package com.omongole.fred.notesapp.di

import android.content.Context
import androidx.room.Room
import com.omongole.fred.notesapp.data.database.NoteDB
import com.omongole.fred.notesapp.data.database.NoteDao
import com.omongole.fred.notesapp.data.database.NoteTypeConverter
import com.omongole.fred.notesapp.data.repo.NoteRepository
import com.omongole.fred.notesapp.domain.repository.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNoteDatabase( @ApplicationContext context: Context ) : NoteDB =
        Room.databaseBuilder( context, NoteDB::class.java, "note database" ).addTypeConverter(NoteTypeConverter()).build()

    @Provides
    @Singleton
    fun providesNoteDao( noteDb: NoteDB ) : NoteDao =
        noteDb.noteDao

    @Provides
    @Singleton
    fun providesNoteRepository( noteDao: NoteDao ) : NoteRepository =
        NoteRepositoryImpl( noteDao )
}