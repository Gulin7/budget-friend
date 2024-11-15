package com.example.budgetfriend.domain

import java.util.Date

data class Income(
    val incomeId: String,
    val source: String,
    val amount: Int,
    val currency: Currency,
    val date: Date?
)
