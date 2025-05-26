package com.seryjnyy.notetaker.features.notes.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seryjnyy.notetaker.data.domain.model.Note
import com.seryjnyy.notetaker.data.domain.model.toNoteEntity
import com.seryjnyy.notetaker.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
) : ViewModel() {
    private val _id = MutableStateFlow<Long?>(null)
    private fun setId(id: Long?) {
        _id.value = id
    }

    private fun setTitle(title: String) {
        _note.update {
            it.copy(title = title)
        }
    }

    private fun setContent(content: String) {
        _note.update {
            it.copy(content = content)
        }
    }

    private val _note = MutableStateFlow(
        Note(
            id = 0,
            title = "",
            content = "",
            timestampEpochMillis = System.currentTimeMillis()
        )
    )


    val note = _note.asStateFlow()
    private val _initialLoadedNote = MutableStateFlow<Note?>(null)

    private fun loadNote(id: Long) {
        viewModelScope.launch {
            val res = noteRepository.observeNote(id).first()
            if (res != null) {
                _note.value = res
                _initialLoadedNote.value = res
            }
        }
    }

    private fun saveNote() {
        val currentNote = note.value
        if (_initialLoadedNote.value?.title == currentNote.title && _initialLoadedNote.value?.content == currentNote.content) return

        viewModelScope.launch {

            if (currentNote.id == 0L) {
                noteRepository.insertNote(currentNote)
            } else {
                noteRepository.upsertNote(currentNote.toNoteEntity())
            }

        }
    }

    fun onAction(action: NotesDetailActions) {
        when (action) {
            is NotesDetailActions.SetNoteId -> setId(action.id)
            is NotesDetailActions.SetTitle -> setTitle(action.title)
            is NotesDetailActions.SetContent -> setContent(action.content)
            is NotesDetailActions.SaveNote -> saveNote()
            is NotesDetailActions.LoadNote -> loadNote(action.id)
        }
    }
}