package com.seryjnyy.notetaker.features.notes.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListScreen(
    navigateToNoteDetail: (Long) -> Unit,
    navigateToNewNote:()->Unit,
    viewModel: NoteListViewModel = hiltViewModel()
){
    val notes by viewModel.notes.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notes") },
            )
        },
        floatingActionButton ={
            FloatingActionButton(onClick = { navigateToNewNote() }) {
                Icon(Icons.Filled.Add, contentDescription = "Add note")
            }
        }
    ) {
        innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding).fillMaxWidth().padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ){
            items(items=notes, key = {it.id}){note ->
                NoteCard(
                    onClick = {
                        navigateToNoteDetail(note.id)
                    },
                    title = "${note.id}",
                    desc = "???",
                    timestampSeconds = Instant.now().epochSecond
                )
            }
        }
    }
}

@Composable
fun NoteCard(
    onClick: () -> Unit,
    title:String,
    desc:String,
    timestampSeconds:Long
){
    val formattedDateTime = remember {
        val dateTime = Instant.ofEpochSecond(timestampSeconds).atZone(ZoneId.systemDefault()).toLocalDateTime()
        when(dateTime.toLocalDate() == LocalDate.now()){
            true -> dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
            false -> dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
        }
    }

    OutlinedCard(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Text(title, style = MaterialTheme.typography.labelLarge, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text(desc, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 6, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(16.dp))
            Text(formattedDateTime, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }

}
