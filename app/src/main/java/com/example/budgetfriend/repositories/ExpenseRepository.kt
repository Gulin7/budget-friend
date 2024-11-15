package com.example.budgetfriend.repositories

import com.example.budgetfriend.domain.Expense
import com.example.budgetfriend.utils.sampleExpenses

class ExpenseRepository {
    private val expenses: MutableList<Expense> = sampleExpenses.toMutableList()

    fun getExpenses(): List<Expense>
    {
        return this.expenses.toList()
    }

    fun addExpense(expenseToAdd: Expense)
    {
        this.expenses.add(expenseToAdd)
    }

    fun updateExpense(expenseToUpdate: Expense)
    {
        this.expenses.forEachIndexed { index, expense ->
            expense.takeIf { it.expenseId == expenseToUpdate.expenseId }?.let {
                this.expenses[index] = it.copy(
                    expenseId = expenseToUpdate.expenseId,
                    description = expenseToUpdate.description,
                    amount = expenseToUpdate.amount,
                    currency = expenseToUpdate.currency,
                    date = expenseToUpdate.date,
                    category = expenseToUpdate.category
                )
            }
        }
    }

    fun deleteExpense(expenseId: String)
    {
        this.expenses.removeIf { it.expenseId == expenseId}
    }
}