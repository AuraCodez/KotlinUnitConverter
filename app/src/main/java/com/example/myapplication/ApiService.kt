package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyAPI {
    @GET("v2/currency/list")
    fun getCurrency(@Query("apiKey") apiKey: String): Call<CurrencyResponse>

}

