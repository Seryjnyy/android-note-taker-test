package com.seryjnyy.notetaker.features.settings.ui

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.google.firebase.auth.FirebaseUser

@Composable
fun SettingSyncSection(
    isUserAuthenticated : Boolean,
    isSyncOn : Boolean
){
    var isOpenDeleteDialog by rememberSaveable { mutableStateOf(false) }

    if (isOpenDeleteDialog) {
        WarningDeleteDialog(
            text = "This action cannot be undone. Are you sure you want to delete all your data in the cloud?",
            onDismiss = { isOpenDeleteDialog = false },
            onAction = {
                isOpenDeleteDialog = false
            }
        )
    }

    SettingPageSection(
        sectionTitle = "Sync"
    ) {
        SettingButtonWithAction(
            title = "Sync is ${ if (isSyncOn && isUserAuthenticated) "on" else "off" }",
            desc = "Click to turn  ${ if ((isSyncOn && isUserAuthenticated).not()) "on" else "off" } sync",
            onClick = {
            },
            isEnabled = isUserAuthenticated,
            actionContent = {
                Text("Last sync at 12:00", color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
            }
        )
        SettingButtonWithAction(
            title = "Force sync",
            desc = "Click to force a sync to happen.",
            onClick = {
            },
            isEnabled = isUserAuthenticated,
            actionContent = {

            }
        )
        SettingButtonWithAction(
            title = "Delete data in cloud",
            desc = "Permanently delete all your data in the cloud.",
            onClick = {
            },
            isEnabled = isUserAuthenticated,
            actionContent = {
                Button(
                    enabled = isUserAuthenticated,
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.error,
                        disabledContainerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.5f),
                        disabledContentColor = MaterialTheme.colorScheme.error.copy(alpha = 0.5f)
                    ),
                    onClick = {
                        isOpenDeleteDialog = true
                    }) {
                    Text("Delete")
                }
            }
        )
    }
}

@Composable
fun SettingSyncAccountSection(
    user: FirebaseUser?,
    signOut: () -> Unit,
    navigateToLoginScreen : () -> Unit
){

    SettingPageSection(
        sectionTitle = "Account"
    ) {
        SettingButtonWithAction(
            title = user?.displayName ?: "You're not signed in",
            desc = user?.email ?: "You need to log in for sync",
            onClick = {
                    if(user == null) navigateToLoginScreen()

                      },
            actionContent = {
                if(user == null){
                    Button(onClick = navigateToLoginScreen) {
                        Text(text = "Log in or Register")
                    }
                }else{
                    FilledTonalButton (onClick = signOut) {
                        Text(text = "Sign out")
                    }
                }
        }
        )
    }
}