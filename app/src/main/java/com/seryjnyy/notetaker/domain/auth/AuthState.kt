package com.seryjnyy.notetaker.domain.auth

import com.google.firebase.auth.FirebaseUser

sealed class AuthState {
    data object Initial : AuthState()
    data object Unauthenticated : AuthState()
    data class Authenticated(val user: FirebaseUser) : AuthState()
}