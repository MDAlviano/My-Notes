package com.alviano.app.mynotes.data

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    val readAllData: LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

}