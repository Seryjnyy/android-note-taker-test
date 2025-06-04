package com.seryjnyy.notetaker.domain.repository

import com.seryjnyy.notetaker.data.entity.local.NoteLocalEntity
import com.seryjnyy.notetaker.data.entity.remote.NoteRemoteEntity
import com.seryjnyy.notetaker.data.model.Note
import com.seryjnyy.notetaker.domain.sync.SyncReport
import com.seryjnyy.notetaker.domain.utils.RepoResult
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNoteStream(id:String) : Flow<Note?>
    fun getUserNotesStream(userId:String) : Flow<List<Note>>
    fun getUnclaimedNotesStream() : Flow<List<Note>>
    suspend fun createNote(title:String, content:String) : RepoResult<Note>
    suspend fun updateNote(note:Note) : RepoResult<Note>
    suspend fun deleteNote(noteId:String, ownerId:String?) : RepoResult<Unit>
    suspend fun syncNotes(userId: String) : RepoResult<SyncReport<NoteLocalEntity, NoteRemoteEntity>>
    suspend fun claimAllUnclaimedNotes() : RepoResult<Unit>
}