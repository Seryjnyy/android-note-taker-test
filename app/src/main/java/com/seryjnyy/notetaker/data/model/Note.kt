package com.seryjnyy.notetaker.data.model

/**
 * External data layer representation.
 */
data class Note(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long,

    // Sync fields
    val userId: String?,
    val needsSync: Boolean,
    val isDeleted: Boolean,
    val lastSyncedAt: Long?,
)