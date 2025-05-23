package com.seryjnyy.notetaker.features.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

/**
 * A generic setting page with a top bar, bottom bar, and a list of sections.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingPage(
    navController: NavHostController,
    topBarTitle: String,
    topBarIcon: @Composable () -> Unit = {},
    topBarActions: @Composable () -> Unit = {},
    listOfSections: List<@Composable () -> Unit>,
    snackbarHostState: SnackbarHostState? = null
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = {
            snackbarHostState?.let { SnackbarHost(hostState = snackbarHostState) }
        },
        topBar = {
            LargeTopAppBar(
                actions = {
                    topBarActions()
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.largeTopAppBarColors(),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        topBarIcon()
                        Text(topBarTitle)
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        },

    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            listOfSections.forEach item@{ section ->
                item {
                    section()
                }
            }
        }
    }
}


/**
 * A generic section of a setting page. It contains a title and a list of settings.
 */
@Composable
fun SettingPageSection(sectionTitle: String, listOfSettings: @Composable () -> Unit) {
    Column {
        Text(
            sectionTitle,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Column {
            listOfSettings()
        }
    }
}