package com.example.myapplication

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("convert")
    suspend fun getConvertCurrency(
        @Query("access_key") access_key: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double
    ) : Response<ApiResponse>

    @GET("symbols")
    suspend fun getCurrencySymbols(
        @Query("access_key") access_key: String,
    ) : Response<SymbolResponse>

}


