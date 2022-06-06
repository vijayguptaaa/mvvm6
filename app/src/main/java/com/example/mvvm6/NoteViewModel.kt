package com.example.mvvm6

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var repository : NoteRepository
    //livedata
    val allNotes : LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabae(application).getNoteDao()
        val repository =NoteRepository(dao)
        allNotes = repository.allNotes
    }
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }
    fun insertNote(note: Note) =viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }
}