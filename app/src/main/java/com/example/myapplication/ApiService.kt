package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("latest")
    fun getCurrency(@Query("base") base: String): Call<CurrencyResponse>
}