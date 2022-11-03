package com.challenge.cstvapp.di

import com.challenge.cstvapp.services.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class ServiceModules {
    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create<ApiServices>()
}