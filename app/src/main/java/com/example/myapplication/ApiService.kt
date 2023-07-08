package com.example.myapplication

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("convertcurrency")
    suspend fun getConvertCurrency(
        @Header("X-Api-Key") apiKey: String,
        @Query("have") have: String,
        @Query("want") want: String,
        @Query("amount") amount: Double,
    ) : Response<CurrencyResponse>

    @GET("symbols")
    suspend fun getCurrencySymbols(
        @Query("access_key") access_key: String,
    ) : Response<SymbolResponse>



}


