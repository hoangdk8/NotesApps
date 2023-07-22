package com.example.notesapp.note.data.model.dataroom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAllNotes():List<Note>
    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)
    @Query("DELETE FROM notes WHERE id = :id")
    fun deleteById(id: Long)
    @Query("SELECT * FROM notes WHERE id = :Id")
    fun getNoteById(Id: Long): Note?

}
