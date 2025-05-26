package com.seryjnyy.notetaker.features.notes.ui.detail

sealed interface NotesDetailActions{
    data class SetNoteId(val id: Long?): NotesDetailActions
    data class SetTitle(val title: String): NotesDetailActions
    data class SetContent(val content: String): NotesDetailActions
    data object SaveNote : NotesDetailActions
    data class LoadNote(val id: Long): NotesDetailActions
}