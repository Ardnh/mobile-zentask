package com.example.zentask.ui.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.zentask.ui.theme.ZenTaskTheme

@Composable
fun LoginView() {
    Text(
        text = "Hello Login !",
    )
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    ZenTaskTheme {
        LoginView()
    }
}
