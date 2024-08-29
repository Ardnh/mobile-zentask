package com.example.zentask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zentask.ui.main.LoginView
import com.example.zentask.ui.main.ProjectDetailsView
import com.example.zentask.ui.main.ProjectView
import com.example.zentask.ui.main.RegisterView
import com.example.zentask.ui.theme.ZenTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZenTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "loginview"
    ) {

        composable(route = "loginview") {
            LoginView()
        }

        composable(route = "registerview") {
            RegisterView()
        }

        composable( route = "projectview" ) {
            ProjectView()
        }

        composable( route = "projectdetailview" ) {
            ProjectDetailsView()
        }

    }

}

@Preview(showBackground = true)
@Composable
fun NavigationPreview() {
    ZenTaskTheme {
        Navigation()
    }
}