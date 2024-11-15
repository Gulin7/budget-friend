package com.example.budgetfriend.domain

import java.util.Date

data class Expense(
    val expenseId: String,
    val description: String,
    val amount: Int,
    val currency: Currency,
    val date: Date?,
    val category: ExpenseCategory,
)
