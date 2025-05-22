package com.seryjnyy.notetaker.data.domain.model

data class Note(
    val id : Long,
    val title:String,
    val content:String,
    val timestampEpochMillis:Long
)