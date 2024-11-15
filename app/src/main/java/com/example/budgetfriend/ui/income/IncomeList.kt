package com.example.budgetfriend.ui.income

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
import com.example.budgetfriend.utils.convertToFormat
import com.example.budgetfriend.viewmodels.IncomesViewModel

@Composable
fun IncomeList(
    modifier: Modifier = Modifier,
    onEditIncome: (String) -> Unit,
    incomesViewModel: IncomesViewModel
) {
    val incomesViewModelData by incomesViewModel.incomes.collectAsState()

    if (incomesViewModelData.filteredIncome.isEmpty())
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            Text(
                "No income found!",
                fontSize = 24.sp
            )
        }
    else
        LazyColumn(modifier = modifier) {
            itemsIndexed(incomesViewModelData.filteredIncome) { _, income ->
                IncomeCard(income = income,
                    onEditIncome = onEditIncome,
                    onDeleteIncome = { incomesViewModel.deleteIncome(income.incomeId) })
            }
        }
}