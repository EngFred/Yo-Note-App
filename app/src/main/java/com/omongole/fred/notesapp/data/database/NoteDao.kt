package com.omongole.fred.notesapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.omongole.fred.notesapp.data.modal.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note ORDER BY createdDate DESC")
    fun getAllNotes() : Flow<List<Note>>

    @Query("SELECT * FROM note WHERE isBookMarked = 1 ORDER BY createdDate DESC")
    fun getBookMarkedNotes() : Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id =:id ORDER BY createdDate")
    fun getNoteById( id: Long ) : Flow<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote( note: Note )

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote( note: Note )

    @Delete
    suspend fun deleteNote( note: Note )

}