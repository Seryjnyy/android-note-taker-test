package com.seryjnyy.notetaker.data.entity.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteLocalEntity(
    @PrimaryKey val id: String,
    val title: String,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long,

    // Sync fields
    val userId: String?,
    val isDeleted: Boolean,
    val needsSync: Boolean,
    val lastSyncedAt: Long?
)