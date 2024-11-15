package com.example.budgetfriend.ui.expense

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.budgetfriend.viewmodels.ExpensesViewModel

@Composable
fun ExpenseList(
    modifier: Modifier = Modifier,
    onEditExpense: (String) -> Unit,
    expensesViewModel: ExpensesViewModel
) {
    val expensesViewModelData by expensesViewModel.expenses.collectAsState()

    if (expensesViewModelData.filteredExpenses.isEmpty())
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            Text(
                "No expense found!",
                fontSize = 24.sp
            )
        }
    else
        LazyColumn(modifier = modifier) {
            itemsIndexed(expensesViewModelData.filteredExpenses) { _, expense ->
                ExpenseCard(expense = expense,
                    onEditExpense = onEditExpense,
                    onDeleteExpense = { expensesViewModel.deleteExpense(expense.expenseId) })
            }
        }
}