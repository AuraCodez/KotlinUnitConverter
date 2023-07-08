package com.example.myapplication

data class CurrencyResponse(
    val old_amount: Double,
    val old_currency: String,
    val new_currency: String,
    val new_amount: Double
)

