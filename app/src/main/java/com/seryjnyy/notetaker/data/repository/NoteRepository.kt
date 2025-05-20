package com.seryjnyy.notetaker.data.repository

import com.seryjnyy.notetaker.data.local.db.dao.NoteDao
import com.seryjnyy.notetaker.data.local.db.entity.NoteEntity

class NoteRepository(
    private val noteDao: NoteDao
) {
    fun observeAllNotes() = noteDao.observeAllNotes()

    fun observeNote(id: Long) = noteDao.observeNote(id)

    suspend fun upsertNote(note: NoteEntity) = noteDao.upsertNote(note)

    suspend fun deleteNote(note: NoteEntity) = noteDao.deleteNote(note)

    suspend fun deleteNote(id: Long) = noteDao.deleteNote(id)
}