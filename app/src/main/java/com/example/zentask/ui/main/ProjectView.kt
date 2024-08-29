package com.example.zentask.ui.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.zentask.ui.theme.ZenTaskTheme

@Composable
fun ProjectView() {
    Text(
        text = "Hello Project view!"
    )
}

@Preview(showBackground = true)
@Composable
fun ProjectPreview() {
    ZenTaskTheme {
        ProjectView()
    }
}
