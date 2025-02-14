package com.example.zentask.data.repository

import android.util.Log
import com.example.zentask.data.model.CommonResponse
import com.example.zentask.data.model.LoginRequest
import com.example.zentask.data.model.LoginResponse
import com.example.zentask.data.model.RegisterRequest
import com.example.zentask.data.model.ResponseWithData
import com.example.zentask.data.source.remote.AuthRemoteDataSource
import javax.inject.Inject

interface AuthRepository {
    suspend fun login(req: LoginRequest): Result<ResponseWithData<LoginResponse>?>
    suspend fun register(req: RegisterRequest): Result<CommonResponse?>
}
class AuthRepositoryImpl @Inject constructor(private val remoteDataSource: AuthRemoteDataSource): AuthRepository {

    override suspend fun login(req: LoginRequest): Result<ResponseWithData<LoginResponse>?> {

        Log.d("Login", "login request")
        Log.d("Login", "Request: $req")

        return remoteDataSource.login(req)
    }

    override suspend fun register(req: RegisterRequest): Result<CommonResponse?> {
        return remoteDataSource.register(req)
    }

}