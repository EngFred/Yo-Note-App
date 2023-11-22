package com.omongole.fred.notesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.omongole.fred.notesapp.data.modal.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(NoteTypeConverter::class)
abstract class NoteDB : RoomDatabase() {
    abstract val noteDao: NoteDao
}