package com.example.budgetfriend.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.budgetfriend.R
import com.example.budgetfriend.ui.components.AmountTextField
import com.example.budgetfriend.utils.convertToFormat
import com.example.budgetfriend.ui.components.CustomButton
import com.example.budgetfriend.ui.components.CustomDatePickerDialog
import com.example.budgetfriend.ui.components.CustomTextField
import com.example.budgetfriend.ui.components.OptionPicker
import com.example.budgetfriend.viewmodels.ExpensesViewModel

@Composable
fun AddOrEditExpenseScreen(
    expenseId: String? = null,
    expensesViewModel: ExpensesViewModel,
    navController: NavController
) {
    val givenExpense = expensesViewModel.findExpenseById(expenseId)
    var expenseDescription by remember { mutableStateOf(givenExpense?.description ?: "") }
    var expenseAmount by remember { mutableIntStateOf(givenExpense?.amount ?: 0) }
    var expenseCurrency by remember { mutableStateOf(givenExpense?.currency ?: "EUR") }
    var date by remember { mutableStateOf(givenExpense?.date?.convertToFormat() ?: "") }
    var showDatePicker by remember { mutableStateOf(false) }
    var expenseCategory by remember { mutableStateOf(givenExpense?.category?.toString() ?: "Other") }

    // List of currency options
    val currencyOptions = listOf("RON", "EUR", "USD")
    // List of category options
    val categoryOptions = listOf("FOOD", "BILLS", "RENT", "SUBSCRIPTION", "OTHER")

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
                if (givenExpense != null) "Edit expense" else "Add expense",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            CustomTextField(
                value = expenseDescription,
                onValueChanged = { expenseDescription = it },
                placeHolderText = "Description"
            )

            AmountTextField(
                value = expenseAmount,
                onValueChanged = { expenseAmount = it },
                placeHolderText = "Amount",
                modifier = Modifier.height(144.dp)
            )

            // Date Picker Row
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Date",
                        color = colorResource(R.color.primary_text),
                        fontSize = 16.sp
                    )

                    if (date.isNotEmpty())
                        Text(
                            text = date,
                            color = colorResource(R.color.primary_text),
                            fontSize = 14.sp
                        )
                }
                Icon(
                    painter = painterResource(id = R.drawable.date_picker),
                    contentDescription = "Date picker icon",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { showDatePicker = true },
                    tint = colorResource(R.color.primary_text)
                )
            }

            // Category Picker Row
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Category",
                    color = colorResource(R.color.primary_text),
                    fontSize = 16.sp
                )

                OptionPicker(
                    onItemSelected = { expenseCategory = it },
                    category = givenExpense?.category?.category,
                    options = categoryOptions
                )
            }

            // Currency Picker Row
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Currency",
                    color = colorResource(R.color.primary_text),
                    fontSize = 16.sp
                )

                OptionPicker(
                    onItemSelected = { expenseCurrency = it },
                    category = givenExpense?.currency?.currency,
                    options = currencyOptions
                )
            }
        }

        if (showDatePicker) {
            CustomDatePickerDialog(
                onDateSelected = { date = it },
                onDismiss = { showDatePicker = false }
            )
        }

        CustomButton(
            onClick = {
                if (givenExpense != null) {
                    expensesViewModel.updateExpense(
                        expenseId = givenExpense.expenseId,
                        description = expenseDescription,
                        amount = expenseAmount,
                        currency = expenseCurrency.toString(),
                        date = date,
                        category = expenseCategory
                    )
                } else {
                    expensesViewModel.addExpense(
                        description = expenseDescription,
                        amount = expenseAmount,
                        currency = expenseCurrency.toString(),
                        date = date,
                        category = expenseCategory
                    )
                }

                navController.popBackStack()
            },
            text = "Save",
            containerColor = colorResource(R.color.primary_text),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
