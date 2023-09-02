package com.example.myapplication

object ConverterUtils {

    fun isNumeric(input: String): Boolean {
        return input.toDoubleOrNull() != null
    }

    fun reverseMap(countriesMap: HashMap<String, String>): HashMap<String, String> {
        return countriesMap.entries.associate { (k, v) -> v to k }.toMutableMap() as HashMap<String, String>
    }
}

enum class ErrorMessage(val message: String) {
    EMPTY_STRING("")
}

enum class ValidAmount(val message: String) {
    INVALID_AMOUNT("Please enter in a valid amount"),

}
