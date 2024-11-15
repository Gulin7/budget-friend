package com.example.budgetfriend.viewmodels

import com.example.budgetfriend.domain.Income

data class IncomesViewModelData(
    val incomes: List<Income> = emptyList(),
    val filteredIncome: List<Income> = emptyList(),
    val filterKey: String = ""
)
