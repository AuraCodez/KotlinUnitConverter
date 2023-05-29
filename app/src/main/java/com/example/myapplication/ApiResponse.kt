package com.example.myapplication

data class ApiResponse(
    val success: Boolean,
    val query: Query,
    val info: Info,
    val historical: String,
    val date: String,
    val result: Double,
)

data class Query(
    val from: String,
    val to: String,
    val amount: Int,
)

data class Info(
    val timestamp: Long,
    val rate: Double,
)

