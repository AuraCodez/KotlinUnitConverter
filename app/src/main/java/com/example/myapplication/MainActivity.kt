package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.exchangeratesapi.io/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        //To read the API key from the assets folder
        val inputStream = assets.open("apiKey.txt")
        val apiKey = inputStream.bufferedReader().use {
            it.readText()
        }

        val api = retrofit.create(CurrencyAPI::class.java)


    }
}