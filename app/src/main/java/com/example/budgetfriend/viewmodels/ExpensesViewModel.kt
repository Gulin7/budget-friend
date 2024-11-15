package com.example.budgetfriend.viewmodels

import androidx.lifecycle.ViewModel
import com.example.budgetfriend.domain.Currency
import com.example.budgetfriend.domain.Expense
import com.example.budgetfriend.domain.ExpenseCategory
import com.example.budgetfriend.utils.convertStringToCategory
import com.example.budgetfriend.utils.convertStringToCurrency
import com.example.budgetfriend.repositories.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
): ViewModel() {

    private val _expenses = MutableStateFlow(ExpensesViewModelData())
    val expenses: StateFlow<ExpensesViewModelData> = _expenses.asStateFlow()

    init {
        _expenses.value = ExpensesViewModelData(
            this.expenseRepository.getExpenses(), this.expenseRepository.getExpenses()
        )
    }

    private fun createExpense(expenseId: String? = null,
                              description: String,
                              amount: Int,
                              currency: String,
                              date: String,
                              category: String) : Expense
    {
        val expenseDate = if (date.isEmpty()) {
            null
        } else {
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(date)
        }

        return Expense(
            expenseId = expenseId ?: UUID.randomUUID().toString(),
            description = description,
            amount = amount,
            currency = convertStringToCurrency(currency),
            date = expenseDate,
            category = convertStringToCategory(category)
        )
    }

    fun addExpense(description: String,
                   amount: Int,
                   currency: String,
                   date: String,
                   category: String)
    {
        val expenseToAdd = this.createExpense(
            description = description,
            amount = amount,
            currency = currency,
            date = date,
            category = category
        )

        _expenses.value = _expenses.value.copy(
            expenses = _expenses.value.expenses + expenseToAdd,
            filteredExpenses = _expenses.value.filteredExpenses + expenseToAdd,
            filterKey = ""
        )

        this.expenseRepository.addExpense(expenseToAdd)
    }

    fun updateExpense(expenseId: String,
                      description: String,
                      amount: Int,
                      currency: String,
                      date: String,
                      category: String

    )
    {
        val expenseToUpdate = this.createExpense(
            expenseId, description, amount, currency, date, category
        )

        val expenses = _expenses.value.expenses.map {
            if (it.expenseId == expenseId)
                expenseToUpdate
            else
                it
        }

        _expenses.value = _expenses.value.copy(expenses = expenses, filteredExpenses = expenses, filterKey="")

        this.expenseRepository.updateExpense(expenseToUpdate)
    }

    fun deleteExpense(expenseId: String)
    {
        this._expenses.value = this._expenses.value.copy(
            expenses = this._expenses.value.expenses.filter { it.expenseId != expenseId },
            filteredExpenses = this._expenses.value.filteredExpenses.filter { it.expenseId != expenseId }
        )
        this.expenseRepository.deleteExpense(expenseId)
    }

    fun updateFilterKey(newFilterKey: String) {
        this._expenses.value = this._expenses.value.copy(filterKey = newFilterKey,
            filteredExpenses = this._expenses.value.expenses.filter { it.description.contains(newFilterKey) })
    }

    fun findExpenseById(expenseId: String?): Expense? {
        return this._expenses.value.expenses.find { it.expenseId == expenseId }
    }
}