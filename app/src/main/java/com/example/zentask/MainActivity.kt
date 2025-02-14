package com.example.zentask

import DashboardLayout
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zentask.ui.main.CategoryView
import com.example.zentask.ui.main.DashboardView
import com.example.zentask.ui.main.LoginView
import com.example.zentask.ui.main.ProjectDetailsView
import com.example.zentask.ui.main.ProjectView
import com.example.zentask.ui.main.RegisterView
import com.example.zentask.ui.theme.ZenTaskTheme
import com.example.zentask.utils.authUtils.GoogleAuthClient
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi GoogleAuthClient di dalam onCreate tanpa lazy
        val appContext = applicationContext
        val googleAuthClient = GoogleAuthClient(
            context = appContext,
            oneTapClient = Identity.getSignInClient(appContext)
        )

        setContent {
            ZenTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Passing googleAuthClient and appContext to the Navigation composable
                    Navigation(googleAuthClient, appContext)
                }
            }
        }
    }
}

@Composable
fun Navigation(googleAuthClient: GoogleAuthClient, context: Context) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    
    NavHost(
        navController = navController,
        startDestination = "loginview"
    ) {

        composable(route = "loginview") {
            LoginView(navController = navController)
        }

        composable(route = "registerview") {
            RegisterView(navController)
        }

        composable( route = "projectview" ) {
            ProjectView(navController)
        }

        composable(route = "categoryview"){
            CategoryView(navController)
        }

        composable( route = "projectdetailview" ) {
            ProjectDetailsView(navController)
        }

        composable( route = "dashboardview" ) {
            DashboardView(navController)
        }

        composable( route = "dashboardlayout" ){
            DashboardLayout(navController)
        }

    }

}