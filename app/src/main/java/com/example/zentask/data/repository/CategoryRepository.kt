package com.example.zentask.data.repository

import com.example.zentask.data.model.CategoryQueryParams
import com.example.zentask.data.model.CategoryResponse
import com.example.zentask.data.model.CommonResponse
import com.example.zentask.data.model.CreateCategoryRequest
import com.example.zentask.data.model.ResponseWithData
import com.example.zentask.data.model.UpdateCategoryRequest
import com.example.zentask.data.source.remote.CategoryRemoteDataSource
import javax.inject.Inject

interface CategoryRepository {
    suspend fun getCategory(req: CategoryQueryParams): Result<ResponseWithData<CategoryResponse>?>
    suspend fun updateCategory(id: String, req: UpdateCategoryRequest): Result<CommonResponse?>
    suspend fun createCategory(req: CreateCategoryRequest): Result<CommonResponse?>
    suspend fun deleteCategory(id: String): Result<CommonResponse?>
}

class CategoryRepositoryImpl @Inject constructor(
    private val categoryRemoteDataSource: CategoryRemoteDataSource
) : CategoryRepository {

    override suspend fun getCategory(req: CategoryQueryParams): Result<ResponseWithData<CategoryResponse>?> {
        return categoryRemoteDataSource.getCategory(req)
    }

    override suspend fun updateCategory(id: String, req: UpdateCategoryRequest): Result<CommonResponse?> {
        return categoryRemoteDataSource.updateCategory(id, req)
    }

    override suspend fun createCategory(req: CreateCategoryRequest): Result<CommonResponse?> {
        return categoryRemoteDataSource.createCategory(req)
    }

    override suspend fun deleteCategory(id: String): Result<CommonResponse?> {
        return categoryRemoteDataSource.deleteCategory(id)
    }
}
