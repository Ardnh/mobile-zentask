package com.example.zentask.data.source.remote

import com.example.zentask.data.model.CategoryQueryParams
import com.example.zentask.data.model.CategoryResponse
import com.example.zentask.data.model.CommonResponse
import com.example.zentask.data.model.CreateCategoryRequest
import com.example.zentask.data.model.ResponseWithData
import com.example.zentask.data.model.UpdateCategoryRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

interface CategoryApiService {

    @GET("/category")
    suspend fun getCategory(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("categoryName") categoryName: String,
        @Query("sortDirection") sortDirection: String,
        @Query("sortBy") sortBy: String
    ): Response<ResponseWithData<CategoryResponse>>

    @POST("/category")
    suspend fun createCategory(@Body req: CreateCategoryRequest) : Response<CommonResponse>

    @PUT("/category/{id}")
    suspend fun updateCategory(@Path("id") id: String, @Body req: UpdateCategoryRequest) : Response<CommonResponse>

    @DELETE("/category/{:id}")
    suspend fun deleteCategory(@Path("id") id: String) : Response<CommonResponse>
}

class CategoryRemoteDataSource @Inject constructor(private val apiService: CategoryApiService) {

    suspend fun getCategory(req: CategoryQueryParams): Result<ResponseWithData<CategoryResponse>?> {

        return handleApiCall { apiService.getCategory(page = req.page, pageSize = req.pageSize, categoryName = req.categoryName, sortDirection = req.sortDirection,  sortBy = req.sortBy) }
    }

    suspend fun createCategory(req: CreateCategoryRequest): Result<CommonResponse?> {

        return handleApiCall { apiService.createCategory(req) }
    }

    suspend fun updateCategory(id: String, req: UpdateCategoryRequest): Result<CommonResponse?> {

        return handleApiCall { apiService.updateCategory(id, req) }
    }

    suspend fun deleteCategory(id: String): Result<CommonResponse?> {

        return handleApiCall { apiService.deleteCategory(id) }
    }

    private suspend fun <T> handleApiCall(apiCall: suspend () -> Response<T>): Result<T?> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Exception("API call failed with error: ${response.code()} | ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}