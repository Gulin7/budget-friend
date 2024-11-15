package com.example.budgetfriend.utils

import com.example.budgetfriend.domain.Currency
import com.example.budgetfriend.domain.ExpenseCategory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.convertToFormat(format: String = "dd/MM/yyyy"): String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.format(this)
}

fun convertStringToCategory(string: String) : ExpenseCategory
{
    when(string.trim().uppercase())
    {
        "SUBSCRIPTION" -> return ExpenseCategory.SUB
        "RENT" -> return ExpenseCategory.RENT
        "BILLS" -> return ExpenseCategory.BILLS
        "FOOD" -> return ExpenseCategory.FOOD
        "OTHER" -> return ExpenseCategory.OTHER
    }

    throw Exception("Invalid Category")
}

fun convertStringToCurrency(currencyString: String): Currency {
    return when (currencyString.trim().uppercase()) {
        "RON", "RON" -> Currency.RON
        "EURO", "EUR" -> Currency.EUR
        "USD" -> Currency.USD
        else -> throw Exception("Invalid Currency: $currencyString")
    }
}

