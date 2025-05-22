package com.seryjnyy.notetaker.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes"
)
data class NoteEntity(
    @PrimaryKey val id: Long = 0,
    val title: String,
    val content: String,
    val timestampEpochMillis: Long
)