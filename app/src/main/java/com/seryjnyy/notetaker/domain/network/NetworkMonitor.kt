package com.seryjnyy.notetaker.domain.network

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val isOnlineStream: Flow<Boolean>
    fun isOnline(): Boolean
}