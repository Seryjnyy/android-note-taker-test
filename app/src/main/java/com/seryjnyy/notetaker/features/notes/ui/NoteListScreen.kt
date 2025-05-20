package com.seryjnyy.notetaker.features.notes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListScreen(
    navigateToNoteDetail: (Long) -> Unit,
    navigateToNewNote:()->Unit
){
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
            item{
                OutlinedCard(
                    modifier = Modifier.fillMaxWidth().clickable { navigateToNoteDetail(1) }
                ) {
                    val timestamp = Instant.now().atZone(ZoneId.systemDefault()).toEpochSecond()
                    val dateTime = Instant.ofEpochSecond(timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime()
                    var formattedDateTime = ""
                    formattedDateTime = if(dateTime.toLocalDate() == LocalDate.now()){
                        dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                    }else{
                        dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
                    }

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ){
                        Text("Note title", style = MaterialTheme.typography.labelLarge, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Content", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 6, overflow = TextOverflow.Ellipsis)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(formattedDateTime, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
    }
}