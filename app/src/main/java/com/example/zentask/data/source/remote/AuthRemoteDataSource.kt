package com.example.zentask.data.source.remote

import com.example.zentask.data.model.CommonResponse
import com.example.zentask.data.model.LoginRequest
import com.example.zentask.data.model.LoginResponse
import com.example.zentask.data.model.RegisterRequest
import com.example.zentask.data.model.ResponseWithData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject

interface AuthApiService {
    @POST("/user/login")
    suspend fun login(@Body req: LoginRequest): Response<ResponseWithData<LoginResponse>?>

    @POST("/user/register")
    suspend fun register(@Body req: RegisterRequest): Response<CommonResponse?>
}

class AuthRemoteDataSource @Inject constructor(private val authApiService: AuthApiService) {

    suspend fun login(req: LoginRequest): Result<ResponseWithData<LoginResponse>?> {
        return handleApiCall { authApiService.login(req) }
    }

    suspend fun register(req: RegisterRequest): Result<CommonResponse?> {
        return handleApiCall { authApiService.register(req) }
    }

    private suspend fun <T> handleApiCall(apiCall: suspend () -> Response<T>): Result<T?> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Exception("API call failed with error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}