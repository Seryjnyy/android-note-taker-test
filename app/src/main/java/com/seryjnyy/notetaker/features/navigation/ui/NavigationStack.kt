package com.seryjnyy.notetaker.features.navigation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.seryjnyy.notetaker.features.navigation.NoteDetail
import com.seryjnyy.notetaker.features.navigation.NoteList
import com.seryjnyy.notetaker.features.notes.ui.NoteDetailScreen
import com.seryjnyy.notetaker.features.navigation.Settings
import com.seryjnyy.notetaker.features.notes.ui.list.NotesListScreen
import com.seryjnyy.notetaker.features.settings.ui.SettingsScreen

@Composable
fun NavigationStack(
) {
val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NoteList()
    ){
        composable<NoteList> {
            NotesListScreen(
                navigateToNewNote = { navController.navigate(NoteDetail(null)) },
                navigateToNoteDetail = { noteId -> navController.navigate(NoteDetail(noteId)) }
            )
    }
        composable<NoteDetail> {
            val noteId = it.toRoute<NoteDetail>().noteId
            NoteDetailScreen(
                noteId = noteId,
                navigateBack = { navController.popBackStack() }
            )
        }

        composable<Settings> {
            SettingsScreen(
                navController = navController
            )
        }
    }
}