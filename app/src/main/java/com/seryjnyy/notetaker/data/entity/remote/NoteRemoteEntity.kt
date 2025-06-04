package com.seryjnyy.notetaker.data.entity.remote

data class NoteRemoteEntity(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),

    // Sync fields
    val userId: String,
    val isDeleted: Boolean,
)