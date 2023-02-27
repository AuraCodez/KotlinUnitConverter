package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Button Click
        val currencyBtn = findViewById<Button>(R.id.currencyButton)
        currencyBtn.setOnClickListener {
            val Intent = Intent(this,Converter::class.java)
            startActivity(Intent)
        }

        val unitConverterBtn = findViewById<Button>(R.id.unitButton)
        unitConverterBtn.setOnClickListener {
            val Intent = Intent(this,UnitConverter::class.java)
            startActivity(Intent)
        }

        val morseCodeBtn = findViewById<Button>(R.id.morseButton)
        morseCodeBtn.setOnClickListener {
            val Intent = Intent(this,MorseCodeConverter::class.java)
            startActivity(Intent)
        }

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