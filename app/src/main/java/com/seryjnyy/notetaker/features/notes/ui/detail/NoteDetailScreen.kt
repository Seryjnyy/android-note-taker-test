package com.seryjnyy.notetaker.features.notes.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(
    noteId: Long?,
    navigateBack: () -> Unit,
    viewModel: NoteDetailViewModel = hiltViewModel(),
) {
    val note by viewModel.note.collectAsStateWithLifecycle()

    LaunchedEffect(noteId) {
        if (noteId != null) {
            viewModel.onAction(
                NotesDetailActions.LoadNote(noteId)
            )
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            note.let {
                viewModel.onAction(
                    NotesDetailActions.SaveNote
                )
            }
        }
    }

    val formattedDateTime = remember {
        val dateTime =
            Instant.ofEpochMilli(note.timestampEpochMillis).atZone(ZoneId.systemDefault())
                .toLocalDateTime()
        when (dateTime.toLocalDate() == LocalDate.now()) {
            true -> dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
            false -> dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
        }
    }


    val isDeleteWarningOpen by remember {
        mutableStateOf(false)
    }

//    if (isDeleteWarningOpen) {
//
//    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                Column {
                    Spacer(Modifier.height(30.dp))
                    BasicTextField(
                        value = note.title,
                        singleLine = true,
                        onValueChange = {
                            viewModel.onAction(
                                NotesDetailActions.SetTitle(it)
                            )
                        },
                        textStyle = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                        cursorBrush = Brush.linearGradient(
                            listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primary
                            )
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        decorationBox = { innerTextField ->
                            if (note.title.isBlank()) {
                                Text(
                                    text = "Title",
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                                )
                            } else {
                                innerTextField()
                            }
                        }
                    )
                    Spacer(Modifier.height(15.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(IntrinsicSize.Max)
                            .padding(bottom = 30.dp)
                    ) {
                        Text(
                            formattedDateTime,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodySmall
                        )
                        VerticalDivider(
                            Modifier.fillMaxHeight(),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            "${note.content.length} characters",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                BasicTextField(
                    value = note.content,
                    onValueChange = {
                        viewModel.onAction(
                            NotesDetailActions.SetContent(it)
                        )
                    },
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface),
                    cursorBrush = Brush.linearGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primary
                        )
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(bottom = 400.dp)
                )

            }
        }
    }
}