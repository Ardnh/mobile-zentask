package com.example.zentask.di

import com.example.zentask.data.source.remote.CategoryApiService
import com.example.zentask.data.source.remote.CategoryRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryModule {

    @Provides
    @Singleton
    fun provideCategoryApiService(retrofit: Retrofit): CategoryApiService {
        return retrofit.create(CategoryApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoryRemoteDataSource(apiService: CategoryApiService) : CategoryRemoteDataSource {
        return CategoryRemoteDataSource(apiService)
    }

}