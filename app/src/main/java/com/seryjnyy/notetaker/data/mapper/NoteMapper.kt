package com.seryjnyy.notetaker.data.mapper

import com.seryjnyy.notetaker.data.entity.local.NoteLocalEntity
import com.seryjnyy.notetaker.data.model.Note
import com.seryjnyy.notetaker.data.entity.remote.NoteRemoteEntity

fun NoteRemoteEntity.asEntity(needsSync : Boolean, lastSyncedAt : Long) =
    NoteLocalEntity(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt,
        updatedAt = updatedAt,
        userId = userId,
        isDeleted = isDeleted,
        needsSync = needsSync,
        lastSyncedAt = lastSyncedAt
    )

fun NoteLocalEntity.asExternalModel() = Note(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    updatedAt = updatedAt,
    userId = userId,
    needsSync = needsSync,
    lastSyncedAt = lastSyncedAt,
    isDeleted = isDeleted,
)

fun NoteLocalEntity.asRemoteEntity() : NoteRemoteEntity {
    if(userId == null){
        throw IllegalStateException("UserId cannot be null")
    }

    return NoteRemoteEntity(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt,
        updatedAt = updatedAt,
        userId = userId,
        isDeleted = isDeleted,
    )
}

fun NoteRemoteEntity.asFirebaseMap() : Map<String, Any> = mapOf(
    "id" to id,
    "title" to title,
    "content" to content,
    "userId" to (userId ?: ""),
    "createdAt" to createdAt,
    "updatedAt" to updatedAt,
    "isDeleted" to isDeleted
)