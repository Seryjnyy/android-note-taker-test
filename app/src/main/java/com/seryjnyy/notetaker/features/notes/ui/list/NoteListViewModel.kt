package com.seryjnyy.notetaker.features.notes.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seryjnyy.notetaker.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    noteRepository: NoteRepository
): ViewModel() {
    val notes = noteRepository.observeAllNotes().stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        emptyList()
    )
}