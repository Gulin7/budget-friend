package com.example.budgetfriend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgetfriend.R

@Composable
fun CustomTextField(
    onValueChanged: (String) -> Unit,
    placeHolderText: String,
    value: String = "",
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf(value) }

    TextField(
        value = text,
        onValueChange = {
            text = it
            onValueChanged(it)
        },
        placeholder = {
            Text(
                placeHolderText,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = colorResource(R.color.secondary_text)
                )
            )
        },
        textStyle = TextStyle(fontSize = 16.sp, color = colorResource(R.color.primary_text)),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color(0xFFE8EDF2))
    )
}

@Composable
fun AmountTextField(
    onValueChanged: (Int) -> Unit,
    placeHolderText: String,
    value: Int = 0,
    modifier: Modifier = Modifier
) {
    // Keep track of the text as a string, and initialize it with the string representation of the value
    var text by remember { mutableStateOf(value.toString()) }

    TextField(
        value = text,
        onValueChange = {
            // Update the text state and try converting the input to an Int
            text = it
            val intValue = it.toIntOrNull() ?: 0
            onValueChanged(intValue)
        },
        placeholder = {
            Text(
                text = placeHolderText,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = colorResource(R.color.secondary_text)
                )
            )
        },
        textStyle = TextStyle(fontSize = 16.sp, color = colorResource(R.color.primary_text)),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color(0xFFE8EDF2))
    )
}
