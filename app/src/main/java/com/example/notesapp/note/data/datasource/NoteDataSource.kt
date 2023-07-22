package com.example.notesapp.note.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesapp.general.AppDataBase
import com.example.notesapp.note.data.model.dataroom.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class NoteDataSource: CoroutineScope {


    suspend fun getListNote(): LiveData<List<Note>> {
        val listNote = withContext(Dispatchers.IO) {
            AppDataBase.getDatabaseClient().noteDao().getAllNotes()
        }
        val result = MutableLiveData<List<Note>>()
        result.value = listNote
        return result
    }
    

    fun addNote(note: Note) {
        launch(Dispatchers.IO) {
            AppDataBase.getDatabaseClient().noteDao().insert(note)
        }
    }
    fun deleteNoteById(id : Long){
        launch(Dispatchers.IO) {
            AppDataBase.getDatabaseClient().noteDao()
                .deleteById(id)
        }
    }
    fun updateNote(note: Note){
        launch(Dispatchers.IO){
            AppDataBase.getDatabaseClient().noteDao().update(note)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}
