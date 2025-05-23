package com.seryjnyy.notetaker.features.settings.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp


@Composable
fun SettingCheckButton(
    title: String,
    desc: String = "",
    checked: Boolean,
    isEnabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit,
) {
    SettingButtonWithAction(
        title = title,
        desc = desc,
        onClick = { onCheckedChange(!checked) },
        isEnabled = isEnabled
    ) {
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun SettingButtonWithAction(
    title: String,
    desc: String = "",
    onClick: () -> Unit,
    isEnabled: Boolean = true,
    actionContent: @Composable () -> Unit,
) {
    SettingButtonContainer(onClick = onClick, isEnabled = isEnabled) {
        SettingInfo(
            title = title,
            desc = desc
        )
        Box(
            modifier = Modifier.padding(start = 12.dp)
        ) {
            SettingAction {
                actionContent()
            }
        }
    }
}

@Composable
fun SettingButtonWithoutAction(
    title: String,
    desc: String = "",
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    SettingButtonContainer(onClick = onClick, isEnabled = isEnabled) {
        SettingInfo(
            title = title,
            desc = desc
        )
    }
}


@Composable
fun SettingInfo(
    title: String,
    desc: String = ""
) {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            title,
            style = MaterialTheme.typography.bodyLarge
        )
        if (desc.isNotEmpty()) {
            Text(
                desc,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

    }
}

@Composable
fun SettingAction(
    content: @Composable () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.End
    ) {
        content()
    }
}

@Composable
fun SettingButtonContainer(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isEnabled: Boolean = true,
    useFixedHeight: Boolean = true,
    content: @Composable () -> Unit,
) {
    Button(
        onClick = { onClick() },
        modifier = modifier.then(
            if (useFixedHeight) Modifier.requiredHeight(70.dp) else Modifier
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RectangleShape,
        enabled = isEnabled
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }
    }
}
