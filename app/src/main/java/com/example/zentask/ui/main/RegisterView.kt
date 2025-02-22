package com.example.zentask.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.zentask.ui.theme.BackgroundColor
import com.example.zentask.ui.theme.Gray100
import com.example.zentask.ui.theme.NunitoExtraBold
import com.example.zentask.ui.theme.NunitoRegular
import com.example.zentask.ui.theme.PrimaryColor
import com.example.zentask.ui.theme.SecondaryColor
import com.example.zentask.viewmodel.LoginViewModel

@Composable
fun RegisterView(navController: NavController) {

    // Instance
    val loginViewModel: LoginViewModel = hiltViewModel<LoginViewModel>()

    // State
    val loginRequest = loginViewModel.loginRequest.collectAsState()
    val validationErrors = loginViewModel.validationErrors.collectAsState()
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
                        value = loginRequest.value.email,
                        interactionSource = interactionUsernameSource,
                        singleLine = true,
                        onValueChange = {
                            isUsernameTouched = true
                            loginViewModel.updateLoginField("username", it)
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
                                if (loginRequest.value.email.isEmpty()) {
                                    Text(
                                        text = "Your username",
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
                        .border(1.4.dp, usernameBorderColor, shape)
                        .padding(1.dp)
                ) {
                    BasicTextField(
                        value = loginRequest.value.email,
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
                                if (loginRequest.value.email.isEmpty()) {
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
                            1.4.dp,
                            if (isUsernameError) Color.Red else passwordBorderColor,
                            shape
                        )
                        .padding(1.dp)
                        .focusRequester(FocusRequester.Default) // Request focus for this field
                ) {
                    BasicTextField(
                        value = loginRequest.value.password,
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
                            if (loginRequest.value.password.isEmpty()) {
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

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF153448))
                ) {
                    Text(text = "Sign Up", color = Color(0xFFFFFFFF))
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { navController.navigate("loginview") },
                    colors = ButtonDefaults.buttonColors(containerColor = Gray100)
                ) {
                    Text(text = "Login", color = PrimaryColor)
                }
            }
        }

    }

}

