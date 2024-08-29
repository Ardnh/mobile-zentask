package com.example.zentask.ui.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.zentask.ui.theme.ZenTaskTheme

@Composable
fun RegisterView() {
    Text(
        text = "Hello Register!",
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    ZenTaskTheme {
        RegisterView()
    }
}
