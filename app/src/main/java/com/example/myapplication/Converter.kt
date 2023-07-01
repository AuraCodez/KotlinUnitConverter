package com.example.myapplication
import RetrofitClient
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jaredrummler.materialspinner.MaterialSpinner
import com.jaredrummler.materialspinner.MaterialSpinnerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Converter : AppCompatActivity() {
<<<<<<< Updated upstream

    private val api: ApiService = RetrofitClient.api
    private lateinit var fromSpinner: MaterialSpinner
    private lateinit var toSpinner: MaterialSpinner
    private lateinit var amountEditText: EditText
    private lateinit var convertButton: Button
    private lateinit var resultEditText: EditText
=======
    private lateinit var viewModel: ConverterViewModel
>>>>>>> Stashed changes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.currency_page)

<<<<<<< Updated upstream
        fromSpinner = findViewById(R.id.fromSpinner)
        toSpinner = findViewById(R.id.toSpinner)
        amountEditText = findViewById(R.id.enterCurrency)
        convertButton = findViewById(R.id.convertButton)
        resultEditText = findViewById(R.id.etSecondCurrency)

        val apiKey = intent.getStringExtra("API_KEY") ?: ""

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
                        populateSpinners(symbols, toSpinner)
                        populateSpinners(symbols, fromSpinner)
                    }
                }
            } catch(e: Exception) {
                e.printStackTrace()
            }

        }
=======
        // If no such key exists in the extra of the intent, default to empty string for error handling
        val apiKey = intent.getStringExtra("API_KEY") ?: ""
        viewModel = ViewModelProvider(this)[ConverterViewModel::class.java]

        val fromSpinner = findViewById<MaterialSpinner>(R.id.spinner);
        val toSpinner = findViewById<MaterialSpinner>(R.id.spinner2);
        val amountEditText = findViewById<EditText>(R.id.enterCurrency);
        val convertButton = findViewById<Button>(R.id.convertButton);
        val resultEditText = findViewById<EditText>(R.id.etSecondCurrency);
>>>>>>> Stashed changes

    }
}