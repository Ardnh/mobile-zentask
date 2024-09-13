package com.example.zentask

import android.app.Activity.RESULT_OK
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zentask.ui.main.DashboardView
import com.example.zentask.ui.main.LoginView
import com.example.zentask.ui.main.ProjectDetailsView
import com.example.zentask.ui.main.ProjectView
import com.example.zentask.ui.main.RegisterView
import com.example.zentask.ui.theme.ZenTaskTheme
import com.example.zentask.utils.authUtils.GoogleAuthClient
import com.example.zentask.viewmodel.LoginViewModel
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val appContext = applicationContext
    private val googleAuthClient by lazy {
        GoogleAuthClient(
            context = appContext,
            oneTapClient = Identity.getSignInClient(appContext)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZenTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
            val loginViewModel = viewModel<LoginViewModel>()
            val state = loginViewModel.signInState.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit) {
                if (googleAuthClient.getSignedInUser() !== null) {
                    navController.navigate("dashboardview")
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if(result.resultCode == RESULT_OK) {
                        scope.launch {
                            val signInResult = googleAuthClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            loginViewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.value.isSignInSuccessful) {
                if(state.value.isSignInSuccessful) {
                    Toast.makeText(
                        context,
                        "Sign in successful",
                        Toast.LENGTH_LONG
                    ).show()

                    navController.navigate("profile")
                    loginViewModel.resetState()
                }
            }

            LoginView(
                signInState = state.value,
                navController = navController,
                onSignInClick = {
                    scope.launch {
                        val signInIntentSender = googleAuthClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }
            )

        }

        composable(route = "registerview") {
            RegisterView(navController)
        }

        composable( route = "projectview" ) {
            ProjectView(navController)
        }

        composable( route = "projectdetailview" ) {
            ProjectDetailsView(navController)
        }

        composable( route = "dashboardview" ) {
            DashboardView(navController)
        }

    }

}