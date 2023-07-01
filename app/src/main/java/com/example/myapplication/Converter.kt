package com.example.myapplication
import RetrofitClient
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.jaredrummler.materialspinner.MaterialSpinner
import com.jaredrummler.materialspinner.MaterialSpinnerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Converter : AppCompatActivity() {

    private val api: ApiService = RetrofitClient.api
    private lateinit var fromSpinner: MaterialSpinner
    private lateinit var toSpinner: MaterialSpinner
    private lateinit var amountEditText: EditText
    private lateinit var convertButton: Button
    private lateinit var resultEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.currency_page)

        fromSpinner = findViewById(R.id.fromSpinner)
        toSpinner = findViewById(R.id.toSpinner)
        amountEditText = findViewById(R.id.enterCurrency)
        convertButton = findViewById(R.id.convertButton)
        resultEditText = findViewById(R.id.etSecondCurrency)

        val apiKey = intent.getStringExtra("API_KEY") ?: ""
        Log.d("ConverterActivity", "API key: $apiKey")
        fetchCurrencySymbols(apiKey)

    }

    private fun populateSpinners(symbols: List<String>, spinner: MaterialSpinner) {
        when(spinner.id) {
            R.id.toSpinner -> {
                val adapter = MaterialSpinnerAdapter(spinner.context, symbols)
                spinner.setAdapter(adapter)

            }
            R.id.fromSpinner -> {
                val adapter = MaterialSpinnerAdapter(spinner.context, symbols)
                spinner.setAdapter(adapter)
            }
        }
    }
    private fun fetchCurrencySymbols(apiKey: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.getCurrencySymbols(apiKey)
                if (response.isSuccessful) {
                    // Get the response body and perform actions on it only if it's not null
                    response.body()?. let {data ->
                        // Convert the map values to a list
                        val symbols = data.symbols.values.toList()
                        Log.d("Converter", "Currency Symbols: $symbols")
                        populateSpinners(symbols, toSpinner)
                        populateSpinners(symbols, fromSpinner)
                    }
                }
            } catch(e: Exception) {
                e.printStackTrace()
            }

        }

    }
}