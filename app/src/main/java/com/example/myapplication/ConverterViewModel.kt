package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConverterViewModel(private val api: ApiService): ViewModel() {
    // LiveData that holds the currency codes
    val currencySymbols = MutableLiveData<List<String>>()

}