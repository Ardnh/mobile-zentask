package com.example.zentask.data.model

data class CommonResponse(
    val code: Int,
    val message: String,
)

data class ResponseWithData<T>(
    val code: Int,
    val message: String,
    val data : T
)

data class ValidationErrors(
    val field: String,
    val error: MutableList<String> = mutableListOf(),
)