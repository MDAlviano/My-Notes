package com.alviano.app.mynotes.repository

import androidx.lifecycle.LiveData
import com.alviano.app.mynotes.data.NoteDao
import com.alviano.app.mynotes.model.Note

class NoteRepository(private val noteDao: NoteDao) {

    val readAllData: LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

}