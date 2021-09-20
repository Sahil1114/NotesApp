package com.example.notesapp.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesapp.db.NoteDatabase
import com.example.notesapp.entity.Notes
import com.example.notesapp.repository.NotesRepository

class NotesViewModel(application: Application) :AndroidViewModel(application) {

    private val repository : NotesRepository

    init {
        val dao=NoteDatabase.getDatabaseInstance(application).getNotesDao()
        repository= NotesRepository(dao)
    }

    fun addNotes(notes: Notes)=repository.insertNotes(notes)

    fun getHighNotes() :LiveData<List<Notes>> = repository.getHighNotes()

    fun getMediumNotes() :LiveData<List<Notes>> = repository.getMediumNotes()

    fun getLowNotes() :LiveData<List<Notes>> = repository.getLowNotes()

    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    fun deleteNotes(id :Int)= repository.deleteNotes(id)

    fun updateNotes(notes: Notes) = repository.updateNotes(notes)


}