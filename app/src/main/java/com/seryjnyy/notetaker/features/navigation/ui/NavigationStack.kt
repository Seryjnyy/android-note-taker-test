package com.seryjnyy.notetaker.features.navigation.ui

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.seryjnyy.notetaker.R
import com.seryjnyy.notetaker.features.navigation.NoteDetail
import com.seryjnyy.notetaker.features.navigation.NoteList
import com.seryjnyy.notetaker.features.navigation.SettingSync
import com.seryjnyy.notetaker.features.navigation.SettingSyncLogin
import com.seryjnyy.notetaker.features.navigation.SettingSyncNav
import com.seryjnyy.notetaker.features.navigation.Settings
import com.seryjnyy.notetaker.features.notes.ui.detail.NoteDetailScreen
import com.seryjnyy.notetaker.features.notes.ui.list.NotesListScreen
import com.seryjnyy.notetaker.features.auth.AuthState
import com.seryjnyy.notetaker.features.auth.AuthViewModel
import com.seryjnyy.notetaker.features.settings.ui.SettingPage
import com.seryjnyy.notetaker.features.settings.ui.SettingSyncAccountSection
import com.seryjnyy.notetaker.features.settings.ui.SettingSyncLoginSection
import com.seryjnyy.notetaker.features.settings.ui.SettingSyncSection
import com.seryjnyy.notetaker.features.settings.ui.SettingsScreen

@Composable
fun NavigationStack(
) {
val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
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
        navigation<SettingSyncNav>(
            startDestination = SettingSync()
        ){
            composable<SettingSync> {

                val authState by authViewModel.authState.collectAsStateWithLifecycle()

                val isAuthenticated = authState is AuthState.Authenticated
                val isSyncOn = true

                SettingPage(
                    navController = navController,
                    topBarTitle = "Sync",
                    topBarIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_sync),
                            contentDescription = "Sync icon"
                        )
                    },
                    listOfSections = listOf(
                        {
                            SettingSyncSection(
                                isUserAuthenticated = isAuthenticated,
                                isSyncOn = isSyncOn && isAuthenticated
                            )
                            SettingSyncAccountSection(
                                navigateToLoginScreen = { navController.navigate(SettingSyncLogin) },
                                user = when(authState){
                                    is AuthState.Authenticated -> (authState as AuthState.Authenticated).user
                                    else -> null
                                },
                                signOut = { authViewModel.signOut() }
                            )
//                        )
                        }
                    )
                )
            }
            composable<SettingSyncLogin> {
                SettingPage(
                    navController = navController,
                    topBarTitle = "Login",
                    topBarIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Person icon"
                        )
                    },
                    listOfSections = listOf(
                        {
                            SettingSyncLoginSection(
                                onSignIn = { navController.popBackStack() }
                            )

                        }
                    )
                )

            }
        }

    }
}