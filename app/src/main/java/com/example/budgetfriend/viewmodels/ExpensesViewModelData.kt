package com.example.budgetfriend.viewmodels

import com.example.budgetfriend.domain.Expense

data class ExpensesViewModelData(
    val expenses: List<Expense> = emptyList(),
    val filteredExpenses: List<Expense> = emptyList(),
    val filterKey: String = ""
)
