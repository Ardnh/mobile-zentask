package com.example.zentask.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import com.example.zentask.ui.theme.ZenTaskTheme

@Composable
fun ProjectDetailsView() {
    Text(
        text = "Hello Project details!"
    )
}

@Preview(showBackground = true)
@Composable
fun ProjectDetailsPreview() {
    ZenTaskTheme {
        ProjectDetailsView()
    }
}
