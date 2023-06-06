package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.jaredrummler.materialspinner.MaterialSpinner

class Converter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.currency_page)

        val fromSpinner = findViewById<MaterialSpinner>(R.id.spinner);
        val toSpinner = findViewById<MaterialSpinner>(R.id.spinner2);
        val amountEditText = findViewById<EditText>(R.id.enterCurrency);
        val convertButton = findViewById<Button>(R.id.convertButton);
        val resultEditText = findViewById<EditText>(R.id.etSecondCurrency);









    }
}