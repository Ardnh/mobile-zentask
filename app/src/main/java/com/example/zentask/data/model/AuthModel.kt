package com.example.zentask.data.model
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)

data class LoginRequest(
    var username: String,
    var password: String
)

data class LoginResponse(
    val token: String,
    val username: String,
)
