package com.example.zentask.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.zentask.R
import com.example.zentask.ui.theme.BackgroundColor
import com.example.zentask.ui.theme.Gray100
import com.example.zentask.ui.theme.NunitoBold
import com.example.zentask.ui.theme.NunitoExtraBold
import com.example.zentask.ui.theme.NunitoRegular
import com.example.zentask.ui.theme.NunitoSemiBold
import com.example.zentask.ui.theme.PrimaryColor
import com.example.zentask.ui.theme.SecondaryColor
import com.example.zentask.viewmodel.LoginViewModel

@Composable
fun LoginView(navController: NavController) {

    // Instance
    val loginViewModel: LoginViewModel = hiltViewModel<LoginViewModel>()
    val context = LocalContext.current

    // State
    val shouldNavigateToDashboard by loginViewModel.shouldNavigateToDashboard.collectAsState()
    val validationErrors = loginViewModel.validationErrors.collectAsState()
    val loginRequest by loginViewModel.loginRequest.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }
    val isUsernameError by remember { mutableStateOf(false) }
    val isPasswordError by remember { mutableStateOf(false) }
    val interactionUsernameSource = remember { MutableInteractionSource() }
    val interactionPasswordSource = remember { MutableInteractionSource() }
    val isUsernameFocused by interactionUsernameSource.collectIsFocusedAsState()
    val isPasswordFocused by interactionPasswordSource.collectIsFocusedAsState()
    var isUsernameTouched by remember { mutableStateOf(false) }
    var isPasswordTouched by remember { mutableStateOf(false) }
    val shape = RoundedCornerShape(10.dp)

    val usernameBorderColor = when {
        isUsernameError -> Color.Red
        isUsernameFocused -> Color(0xFFF1F3F4)
        else -> Color(0xFFF1F3F4)
    }

    val passwordBorderColor = when {
        isPasswordError -> Color.Red
        isPasswordFocused -> Color(0xFFF1F3F4)
        else -> Color(0xFFF1F3F4)
    }

     LaunchedEffect(shouldNavigateToDashboard){
          if(shouldNavigateToDashboard){
              navController.navigate("dashboardlayout")
          }
     }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Column {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 60.dp)
                ) {
                    Text(
                        fontSize = 50.sp,
                        text = "Zen Task",
                        color = PrimaryColor,
                        fontFamily = NunitoExtraBold
                    )
                    Text(
                        text = "Your minimalistic todolist app",
                        color = SecondaryColor,
                        fontFamily = NunitoRegular,
                        fontSize = 15.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(shape)
                        .border(1.4.dp, usernameBorderColor, shape)
                        .padding(1.dp)
                ) {
                    BasicTextField(
                        value = loginRequest.email,
                        interactionSource = interactionUsernameSource,
                        singleLine = true,
                        onValueChange = {
                            isUsernameTouched = true
                            loginViewModel.updateLoginField("email", it)
                        },
                        textStyle = TextStyle(
                            color = Color.Gray,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light,
                            fontFamily = NunitoRegular
                        ),
                        cursorBrush = SolidColor(Color.Gray),
                        decorationBox = { innerText ->

                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.CenterStart,
                                ) {
                                    if (loginRequest.email.isEmpty()) {
                                        Text(
                                            text = "Your email adress",
                                            style = TextStyle(
                                                color = Color.Gray,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Light,
                                                fontFamily = NunitoRegular
                                            )
                                        )
                                    }
                                    innerText()
                                }

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .clip(shape)
                            .background(Color(0xFFF1F3F4))
                            .padding(vertical = 5.dp, horizontal = 15.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(25.dp)
                ) {
                    if (isUsernameTouched) {

                        validationErrors.value.find { it.field == "username" }?.errors?.forEach { error ->
                            Text(
                                fontSize = 12.sp,
                                text = error,
                                color = Color.Red,
                                fontFamily = NunitoRegular,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                        }

                    }
                }

                Box(
                    modifier = Modifier
                        .clip(shape)
                        .background(Color.LightGray)
                        .border(
                            1.4.dp, if (isUsernameError) Color.Red else passwordBorderColor, shape
                        )
                        .padding(1.dp)
                        .focusRequester(FocusRequester.Default) // Request focus for this field
                ) {
                    BasicTextField(
                        value = loginRequest.password,
                        onValueChange = {
                            isPasswordTouched = true
                            loginViewModel.updateLoginField("password", it)
                        },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Light
                        ),
                        cursorBrush = SolidColor(Color.LightGray),
                        visualTransformation = VisualTransformation.None,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        interactionSource = interactionPasswordSource,
                        decorationBox = { innerTextField ->
                            if (loginRequest.password.isEmpty()) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Text(
                                        text = "Your password",
                                        style = TextStyle(
                                            color = Color.Gray,
                                            fontSize = 15.sp,
                                            fontFamily = NunitoRegular,
                                            fontWeight = FontWeight.Light
                                        )
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                innerTextField()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .clip(shape)
                            .background(Color(0xFFF1F3F4))
                            .padding(vertical = 5.dp, horizontal = 15.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Column{
                        if (isPasswordTouched) {
                            validationErrors.value.find { it.field == "password" }?.errors?.forEach { error ->
                                Text(
                                    fontSize = 12.sp,
                                    text = error,
                                    color = Color.Red,
                                    fontFamily = NunitoRegular,
                                    modifier = Modifier.padding(horizontal = 10.dp)
                                )
                            }
                        }
                    }
                }

                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
                ){
                    Text(
                        fontFamily = NunitoSemiBold,
                        text = "Forgot password ?",
                        fontSize = 14.sp,
                        color = PrimaryColor,
                        modifier = Modifier.clickable {
                            // navigate to forgot password view
                        }
                    )
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        loginViewModel.login()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF153448))
                ) {
                    Text(
                        fontFamily = NunitoBold,
                        fontSize = 14.sp,
                        text = "Login",
                        color = Color.White
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Or",
                        fontFamily = NunitoBold,
                        color = PrimaryColor,
                        fontSize = 14.sp
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(Gray100)
                        .height(40.dp)
                        .clickable{
                                  //onSignInClick()
                                  navController.navigate("dashboardview")
                        },
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "Google logo",
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            fontFamily = NunitoSemiBold,
                            fontSize = 14.sp,
                            text = "Continue with google",
                            color = Color(0xFF343a40),
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    fontFamily = NunitoRegular,
                    text = "Don't have an account ? ",
                    fontSize = 14.sp,
                    color = Color(0xFF153448)
                )
                Text(
                    fontFamily = NunitoBold,
                    text = "Sign Up",
                    color = PrimaryColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        navController.navigate("registerview")
                    }
                )
            }
        }
    }

}

