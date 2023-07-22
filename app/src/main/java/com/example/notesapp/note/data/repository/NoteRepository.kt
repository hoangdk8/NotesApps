package com.example.notesapp.note.data.repository

import androidx.lifecycle.LiveData
import com.example.notesapp.note.data.datasource.NoteDataSource
import com.example.notesapp.note.data.model.dataroom.NoteDao
import com.example.notesapp.note.data.model.dataroom.Note

class NoteRepository {
    private val dataSource = NoteDataSource()
    suspend fun getListNote() = dataSource.getListNote()
    fun addNote(note: Note) {
        dataSource.addNote(note)
    }
    fun deleteNoteById(id : Long){
        dataSource.deleteNoteById(id)
    }
    fun updateNote(note: Note) {
        dataSource.updateNote(note)
    }
}
