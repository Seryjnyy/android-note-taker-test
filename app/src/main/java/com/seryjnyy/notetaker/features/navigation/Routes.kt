package com.seryjnyy.notetaker.features.navigation

import kotlinx.serialization.Serializable

@Serializable
class NoteList

@Serializable
class NoteDetail(val noteId: String?)

@Serializable
class Settings

@Serializable
object SettingSyncNav

@Serializable
class SettingSync

@Serializable
object SettingSyncLogin