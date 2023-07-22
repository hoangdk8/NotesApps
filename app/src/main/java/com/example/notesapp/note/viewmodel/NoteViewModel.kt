package com.example.notesapp.note.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.notesapp.note.data.repository.NoteRepository
import com.example.notesapp.note.data.model.dataroom.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus

class NoteViewModel() : ViewModel() {
    private val repository = NoteRepository()
    private val _listNote = MediatorLiveData<List<Note>>()
    val listNote: LiveData<List<Note>>
        get() = _listNote


    fun getListNote() {
        viewModelScope.launch(Dispatchers.Main) {
            _listNote.addSource(repository.getListNote()) {
                _listNote.value = it
            }
        }
    }

    fun addNote(note: Note) {
        repository.addNote(note)
    }
    fun deleteNote(id : Long){
        repository.deleteNoteById(id)
    }
    fun updateNote(note: Note) {
        repository.updateNote(note)
    }

}
