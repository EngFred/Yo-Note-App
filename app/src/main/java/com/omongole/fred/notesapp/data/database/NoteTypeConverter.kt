package com.omongole.fred.notesapp.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.util.Date

@ProvidedTypeConverter
class NoteTypeConverter {

    @TypeConverter
    fun fromDate( date: Date ) : Long = date.time

    @TypeConverter
    fun toDate( long: Long ) : Date = Date(long)
}