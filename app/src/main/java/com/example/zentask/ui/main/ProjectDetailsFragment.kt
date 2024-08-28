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

class ProjectDetailsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Menggunakan ComposeView untuk mengintegrasikan Jetpack Compose dalam Fragment
        return ComposeView(requireContext()).apply {
            setContent {
                ZenTaskTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ProjectDetailsFragment("Login Screen")
                    }
                }
            }
        }
    }

}

@Composable
fun ProjectDetailsFragment(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ProjectDetailsPreview() {
    ZenTaskTheme {
        ProjectDetailsFragment("Register Screen")
    }
}
