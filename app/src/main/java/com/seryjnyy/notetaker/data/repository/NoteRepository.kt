package com.seryjnyy.notetaker.data.repository

import com.seryjnyy.notetaker.data.domain.model.toNote
import com.seryjnyy.notetaker.data.local.db.dao.NoteDao
import com.seryjnyy.notetaker.data.local.db.entity.NoteEntity
import kotlinx.coroutines.flow.map

class NoteRepository(
    private val noteDao: NoteDao
) {
    fun observeAllNotes() = noteDao.observeAllNotes().map{ list -> list.map { noteEntity -> noteEntity.toNote() } }

    fun observeNote(id: Long) = noteDao.observeNote(id).map { it?.toNote() }

    suspend fun upsertNote(note: NoteEntity) = noteDao.upsertNote(note)

    suspend fun deleteNote(note: NoteEntity) = noteDao.deleteNote(note)

    suspend fun deleteNote(id: Long) = noteDao.deleteNote(id)
}