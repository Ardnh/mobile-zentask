package com.example.zentask.di.provides

import com.example.zentask.data.source.remote.ProjectApiService
import com.example.zentask.data.source.remote.ProjectRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProjectModule {

    @Provides
    @Singleton
    fun provideProjectApiService(retrofit: Retrofit): ProjectApiService {
        return retrofit.create(ProjectApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideProjectRemoteDataSource(apiService: ProjectApiService):ProjectRemoteDataSource {
        return ProjectRemoteDataSource(apiService)
    }

}
