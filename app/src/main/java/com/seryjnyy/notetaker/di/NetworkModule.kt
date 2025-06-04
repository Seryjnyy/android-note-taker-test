package com.seryjnyy.notetaker.di

import com.seryjnyy.notetaker.data.network.NetworkMonitorImpl
import com.seryjnyy.notetaker.domain.network.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {
    @Binds
    @Singleton
    abstract fun bindNetworkMonitor(
        networkMonitor: NetworkMonitorImpl
    ) : NetworkMonitor
}