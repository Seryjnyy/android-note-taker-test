package com.seryjnyy.notetaker.features.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun WarningDeleteDialog(
    modifier: Modifier = Modifier,
    text: String,
    onDismiss: () -> Unit,
    onAction: () -> Unit,
) {
    WarningDialog(
        modifier = modifier,
        text = text,
        onDismiss = onDismiss,
        onAction = onAction,
        dialogHeader = { WarningDeleteDialogHeader() }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun WarningDialog(
    modifier: Modifier = Modifier,
    text: String,
    onDismiss: () -> Unit,
    onAction: () -> Unit,
    dialogHeader: @Composable () -> Unit,
) {
    BasicAlertDialog(
        onDismissRequest = onDismiss,
        modifier = modifier.width(328.dp)
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            color = MaterialTheme.colorScheme.surfaceContainer,
        ) {
            Column {
                dialogHeader()
                Text(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp, top = 16.dp)
                        .fillMaxWidth(),
                    text = text,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                )
                DialogButtons(
                    confirmTitle = "Confirm",
                    onConfirmClick = onAction,
                    onCancelClick = onDismiss,
                )
            }
        }
    }
}


@Composable
private fun WarningDeleteDialogHeader(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp, top = 24.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
        )
        Text(
            text = "Delete",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Composable
private fun DialogButtons(
    modifier: Modifier = Modifier,
    enabledConfirm: Boolean = true,
    confirmTitle: String,
    onCancelClick: () -> Unit,
    onConfirmClick: () -> Unit,
) {
    Row(
        modifier = modifier.padding(top = 16.dp, bottom = 16.dp, end = 16.dp, start = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = onCancelClick) {
            Text(
                text = "Cancel",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
            )
        }
        TextButton(enabled = enabledConfirm, onClick = onConfirmClick) {
            Text(
                text = confirmTitle,
                color = when (enabledConfirm) {
                    true -> MaterialTheme.colorScheme.primary
                    false -> MaterialTheme.colorScheme.onSurfaceVariant
                },
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}
