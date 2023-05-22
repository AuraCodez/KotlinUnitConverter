package com.example.myapplication
import RetrofitClient
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currencyBtn = findViewById<Button>(R.id.currencyButton)
        currencyBtn.setOnClickListener {
            val intent = Intent(this,Converter::class.java)
            startActivity(intent)
        }

        val unitConverterBtn = findViewById<Button>(R.id.unitButton)
        unitConverterBtn.setOnClickListener {
            val intent = Intent(this,UnitConverter::class.java)
            startActivity(intent)
        }

        val morseCodeBtn = findViewById<Button>(R.id.morseButton)
        morseCodeBtn.setOnClickListener {
            val intent = Intent(this,MorseCodeConverter::class.java)
            startActivity(intent)
        }

        val inputStream = assets.open("apiKey.txt")
        val apiKey = inputStream.bufferedReader().use {
            it.readText()
        }
        RetrofitClient.setApiKey(apiKey)

        val api = RetrofitClient.api


    }
}