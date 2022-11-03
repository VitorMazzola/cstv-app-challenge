package com.challenge.cstvapp.di

import com.challenge.cstvapp.data.repository.MatchRepository
import com.challenge.cstvapp.data.repository.MatchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun providesMatchesRepository(repositoryImpl: MatchRepositoryImpl): MatchRepository
}