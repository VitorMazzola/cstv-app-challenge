package com.challenge.cstvapp.di

import com.challenge.cstvapp.data.source.MatchDataSource
import com.challenge.cstvapp.data.source.MatchDataSourceRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteModule {

    @Binds
    fun providesMatchDataSourceRemote(dataSource: MatchDataSourceRemote): MatchDataSource
}