package com.seryjnyy.notetaker.features.navigation

import kotlinx.serialization.Serializable

@Serializable
class NoteList

@Serializable
class NoteDetail(val noteId: Long?)

@Serializable
class Settings