package com.example.mvvm6

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    //insert or delete basically background thread par work karte hain  agar background thread par nahi run
    //karenge to hame exception milega
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * From notes_table order by ASC")
    fun getAllNotes() : LiveData<List<Note>>
}