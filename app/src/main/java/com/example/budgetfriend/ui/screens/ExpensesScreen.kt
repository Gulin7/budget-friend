package com.example.budgetfriend.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.budgetfriend.R
import com.example.budgetfriend.ui.components.CustomButton
import com.example.budgetfriend.ui.components.SearchBar
import com.example.budgetfriend.ui.expense.ExpenseList
import com.example.budgetfriend.viewmodels.ExpensesViewModel

@Composable
fun ExpensesScreen(navController: NavController, expensesViewModel: ExpensesViewModel) {
    val expensesViewModelData by expensesViewModel.expenses.collectAsState()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color(0xFFF7FAFA))
            .padding(all = 16.dp)
    ) {
        // Header
        Column {
            Text(
                "Expenses",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp)
            )

            SearchBar(
                expensesViewModel::updateFilterKey,
                searchQuery = expensesViewModelData.filterKey
            )
        }

        ExpenseList(
            modifier = Modifier.weight(1f),
            onEditExpense = { navController.navigate("editExpense/$it") },
            expensesViewModel = expensesViewModel
        )

        CustomButton(
            onClick = {
                navController.navigate("addExpense")
            }, text = "Add expense",
            containerColor = colorResource(R.color.purple_500),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}