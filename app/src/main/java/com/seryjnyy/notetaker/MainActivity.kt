package com.seryjnyy.notetaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.firebase.FirebaseApp
import com.seryjnyy.notetaker.features.navigation.ui.NavigationStack
import com.seryjnyy.notetaker.ui.theme.NoteTakerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        enableEdgeToEdge()
        setContent {
            NoteTakerTheme(
                dynamicColor = false
            ) {
                NavigationStack()
            }
        }
    }
}


