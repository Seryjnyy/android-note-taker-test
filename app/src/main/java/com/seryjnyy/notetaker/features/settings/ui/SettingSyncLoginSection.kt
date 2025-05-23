package com.seryjnyy.notetaker.features.settings.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.seryjnyy.notetaker.features.auth.GoogleSignInUtils

@Composable
fun SettingSyncLoginSection(
    onSignIn: () -> Unit
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    fun onSuccess(
        context: Context,
    ){
        Log.d("AuthScreen", "Logged in")
        Toast.makeText(context, "Logged in", Toast.LENGTH_SHORT).show()
        onSignIn()
    }


    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()){
        GoogleSignInUtils.doGoogleSignIn(
            context=context,
            scope = scope,
            launcher = null,
            login = {
                onSuccess(
                    context = context
                )
            })

    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedButton(
            onClick = {

                GoogleSignInUtils.doGoogleSignIn(
                    context=context,
                    scope = scope,
                    launcher = launcher,
                    login = {
                        onSuccess(
                            context = context
                        )
                    }
                )
            },
        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "Google icon"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Sign in with Google")
        }
    }
}