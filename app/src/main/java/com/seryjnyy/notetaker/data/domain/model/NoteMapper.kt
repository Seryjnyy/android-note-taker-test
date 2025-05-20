package com.seryjnyy.notetaker.data.domain.model

import com.seryjnyy.notetaker.data.local.db.entity.NoteEntity

fun NoteEntity.toNote() : Note{
    return Note(
        id = id
    )
}

fun Note.toNoteEntity() : NoteEntity{
    return NoteEntity(
        id = id
    )
}