package com.seryjnyy.notetaker.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seryjnyy.notetaker.data.entity.local.NoteLocalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao{
    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteStream(id: String): Flow<NoteLocalEntity?>

    @Query("SELECT * FROM notes WHERE userId = :userId")
    fun getUserNotesStream(userId: String): Flow<List<NoteLocalEntity>>

    @Query("SELECT * FROM notes WHERE userId IS NULL")
    fun getUnclaimedNotesStream(): Flow<List<NoteLocalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceNote(note: NoteLocalEntity)

    @Query("SELECT * FROM notes WHERE userId = :userId AND needsSync = 1")
    suspend fun getUserNotesNeedingSync(userId: String): List<NoteLocalEntity>

    @Query("UPDATE notes SET needsSync = 0, lastSyncedAt = :lastSyncedAt WHERE id = :noteId")
    suspend fun markNoteAsSynced(noteId: String, lastSyncedAt : Long)

    @Query("UPDATE notes SET needsSync = 1 WHERE id = :noteId")
    suspend fun markNoteAsNeedingSync(noteId: String)

    @Query("UPDATE notes SET isDeleted = 1, updatedAt = :updatedAt WHERE id = :noteId")
    suspend fun markNoteAsDeleted(noteId: String, updatedAt: Long)

    @Query("DELETE FROM notes WHERE id = :noteId")
    suspend fun deleteNote(noteId: String)

    @Query("UPDATE notes SET userId = :userId, needsSync = 1 WHERE userId IS NULL")
    suspend fun claimAllUnclaimedNotes(userId: String)

    // TODO : Remove testing functions
    @Query("DELETE FROM notes")
    suspend fun deleteAllNotesTESTING()
}