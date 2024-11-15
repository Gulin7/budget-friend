package com.example.budgetfriend.utils

import com.example.budgetfriend.domain.Expense
import com.example.budgetfriend.domain.ExpenseCategory
import com.example.budgetfriend.domain.Income
import com.example.budgetfriend.domain.Currency

import java.util.Date

val dayInMilis = 86400000

val sampleExpenses = listOf(
    Expense(
        expenseId = "1",
        description = "Youtube subscription",
        amount = 20,
        currency = Currency.RON,
        date = Date(System.currentTimeMillis()),
        category = ExpenseCategory.SUB
    ),
    Expense(
        expenseId = "2",
        description = "Pizza",
        amount = 45,
        currency = Currency.RON,
        date = Date(System.currentTimeMillis() - dayInMilis),
        category = ExpenseCategory.FOOD
    ),
    Expense(
        expenseId = "3",
        description = "Rent",
        amount = 190,
        currency = Currency.EUR,
        date = Date(System.currentTimeMillis() - dayInMilis * 6),
        category = ExpenseCategory.OTHER
    ),
)

val sampleIncomes = listOf(
    Income(
        incomeId = "1",
        source = "Frequentis salary",
        amount = 10000,
        currency = Currency.RON,
        date = Date(System.currentTimeMillis() - dayInMilis * 2),
    ),
    Income(
        incomeId = "2",
        source = "Newspaper delivery",
        amount = 100,
        currency = Currency.RON,
        date = Date(System.currentTimeMillis() - dayInMilis * 4),
    ),
)