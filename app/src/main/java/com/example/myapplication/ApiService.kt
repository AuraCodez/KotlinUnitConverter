package com.example.myapplication

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/currency/list") //The endpoint URL
    suspend fun convertCurrency(
        @Query("api_key") access_key: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double
    ) : Response<ApiResponse>

}


