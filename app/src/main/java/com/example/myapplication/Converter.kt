package com.example.myapplication
import RetrofitClient
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
    private val countriesMap = HashMap<String, String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.currency_page)

        fromSpinner = findViewById(R.id.fromSpinner)
        toSpinner = findViewById(R.id.toSpinner)
        amountEditText = findViewById(R.id.enterCurrency)
        convertButton = findViewById(R.id.convertButton)
        resultEditText = findViewById(R.id.convertedAmount)
        val apiKey = intent.getStringExtra("API_KEY") ?: ""

        convertButton.setOnClickListener {
            if (amountEditText.text.toString() == "" || !(isNumeric(amountEditText.text.toString()))) {
                val context = applicationContext
                Toast.makeText(context, "Please enter in a valid amount", Toast.LENGTH_SHORT).show()
            } else {
                convertCurrency(apiKey, fromSpinner.text.toString().trim(), toSpinner.text.toString().trim(), amountEditText.text.toString().trim().toDouble())
            }
        }

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
                    response.body()?. let {data ->
                        countriesMap.putAll(data.symbols) // We get the HashMap from data.symbols and transfer all the data into the new HashMap
                        Log.d("countries", countriesMap.toString())
                        val worldCurrencies = data.symbols.values.toList()
                        val sortedWorldNames = worldCurrencies.sortedWith(compareBy {it})
                        populateSpinners(sortedWorldNames, toSpinner)
                        populateSpinners(sortedWorldNames, fromSpinner)
                    }
                }
            } catch(e: Exception) {
                e.printStackTrace()
            }

        }
    }

    private fun convertCurrency(apiKey: String, from: String, to: String, amount: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.getConvertCurrency(apiKey, from, to, amount)
                if (response.isSuccessful) {
                    response.body(). let {data ->
                        resultEditText.setText(data.toString())
                        Log.d("Amount", "amount $data.toString()")
                    }

                }
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }

    }

    private fun isNumeric(input: String): Boolean {
        return input.toDoubleOrNull() != null
    }
}