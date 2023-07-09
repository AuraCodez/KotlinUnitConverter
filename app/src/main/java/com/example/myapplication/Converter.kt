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
import java.io.IOException

class Converter : AppCompatActivity() {
    private val apiExchangeRates: ApiService = RetrofitClient.apiExchangeRates
    private val apiCurrencyConverter: ApiService = RetrofitClient.apiNinjasConvert

    private lateinit var fromSpinner: MaterialSpinner
    private lateinit var toSpinner: MaterialSpinner
    private lateinit var amountEditText: EditText
    private lateinit var convertButton: Button
    private lateinit var resultEditText: EditText

    private lateinit var apiKey: String
    private val enterValidAmount = "Please enter in a valid amount"
    private val emptyString = ""

    private val countriesMap = HashMap<String, String>()
    private val reverseCountriesMap = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.currency_page)

        fromSpinner = findViewById(R.id.fromSpinner)
        toSpinner = findViewById(R.id.toSpinner)
        amountEditText = findViewById(R.id.enterCurrency)
        convertButton = findViewById(R.id.convertButton)
        resultEditText = findViewById(R.id.convertedAmount)

        apiKey = intent.getStringExtra("API_KEY") ?: ""

        convertButtonIsClicked()

        fetchCurrencySymbols(apiKey)
    }

    private fun populateSpinners(symbols: List<String>, spinner: MaterialSpinner) {
        val adapter = MaterialSpinnerAdapter(spinner.context, symbols)
        spinner.setAdapter(adapter)
    }

    private fun convertButtonIsClicked() {
        convertButton.setOnClickListener {
            if (amountEditText.text.toString() == emptyString || !(isNumeric(amountEditText.text.toString()))) {
                val context = applicationContext
                Toast.makeText(context, enterValidAmount, Toast.LENGTH_SHORT).show()
            } else {
                val fromCountry = fromSpinner.text.toString().trim()
                val toCountry = toSpinner.text.toString().trim()
                convertCurrency(
                    apiKey, reverseCountriesMap[fromCountry]?: emptyString, reverseCountriesMap[toCountry]?: emptyString,
                    amountEditText.text.toString().trim().toDouble())
            }
        }
    }

    private fun fetchCurrencySymbols(apiKey: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiExchangeRates.getCurrencySymbols(apiKey)
                if (response.isSuccessful) {
                    response.body()?. let {data ->
                        countriesMap.putAll(data.symbols) // We get the HashMap from data.symbols and transfer all the data into the new HashMap
                        val worldCurrencies = data.symbols.values.toList()
                        val sortedWorldNames = worldCurrencies.sortedWith(compareBy {it})
                        reverseCountriesMap.putAll(reverseMap(countriesMap))
                        populateSpinners(sortedWorldNames, toSpinner)
                        populateSpinners(sortedWorldNames, fromSpinner)
                    }
                }
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun convertCurrency(apiKey: String, have: String, want: String, amount: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiCurrencyConverter.getConvertCurrency(apiKey, have, want, amount)
                if (response.isSuccessful) {
                    response.body(). let {data ->
                        resultEditText.setText(data?.new_amount.toString())
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

    private fun reverseMap(countriesMap: HashMap<String, String>): HashMap<String, String> {
        return countriesMap.entries.associate { (k, v) -> v to k }.toMutableMap() as HashMap<String, String>
    }

}