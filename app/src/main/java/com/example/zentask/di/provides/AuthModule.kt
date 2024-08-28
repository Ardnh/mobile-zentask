package com.example.zentask.di.provides

import com.example.zentask.data.source.remote.AuthApiService
import com.example.zentask.data.source.remote.AuthRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(apiService: AuthApiService): AuthRemoteDataSource {
        return AuthRemoteDataSource(apiService)
    }

}