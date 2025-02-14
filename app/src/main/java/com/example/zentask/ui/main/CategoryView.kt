package com.example.zentask.ui.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.zentask.viewmodel.ProjectViewModel

@Composable
fun CategoryView(navController: NavController) {

    // Instance
    val projectViewModel: ProjectViewModel = hiltViewModel()
    val localContext = LocalContext.current

    // State

    Text(
        text = "Hello category view!"
    )
}
