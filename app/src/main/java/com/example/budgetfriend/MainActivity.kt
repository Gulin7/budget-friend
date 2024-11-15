package com.example.budgetfriend

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import com.example.budgetfriend.ui.screens.AddOrEditExpenseScreen
import com.example.budgetfriend.ui.screens.AddOrEditIncomeScreen
import com.example.budgetfriend.ui.screens.ExpensesScreen
import com.example.budgetfriend.ui.screens.IncomesScreen
import com.example.budgetfriend.viewmodels.ExpensesViewModel
import com.example.budgetfriend.viewmodels.IncomesViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    val expensesViewModel: ExpensesViewModel = hiltViewModel()
    val incomesViewModel: IncomesViewModel = hiltViewModel()
    MaterialTheme {
        NavHost(navController = navController, startDestination = "expenses-screen") {
            composable("expenses-screen") {
                ExpensesScreen(
                    navController = navController,
                    expensesViewModel = expensesViewModel
                )
            }
            composable(
                route = "editExpense/{expenseId}",
                arguments = listOf(navArgument("expenseId") { type = NavType.StringType })
            ) { backStackEntry ->
                AddOrEditExpenseScreen(
                    backStackEntry.arguments?.getString("expenseId") ?: "",
                    navController = navController,
                    expensesViewModel = expensesViewModel
                )
            }

            composable(route = "addExpense") {
                AddOrEditExpenseScreen(
                    navController = navController,
                    expensesViewModel = expensesViewModel
                )
            }

            composable("incomes-screen") {
                IncomesScreen(
                    navController = navController,
                    incomesViewModel = incomesViewModel
                )
            }
            composable(
                route = "editIncome/{incomeId}",
                arguments = listOf(navArgument("incomeId") { type = NavType.StringType })
            ) { backStackEntry ->
                AddOrEditIncomeScreen(
                    backStackEntry.arguments?.getString("incomeId") ?: "",
                    navController = navController,
                    incomesViewModel = incomesViewModel
                )
            }

            composable(route = "addIncome") {
                AddOrEditIncomeScreen(
                    navController = navController,
                    incomesViewModel = incomesViewModel
                )
            }

        }
    }
}