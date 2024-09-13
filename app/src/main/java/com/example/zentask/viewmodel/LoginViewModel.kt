package com.example.zentask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zentask.data.model.LoginRequest
import com.example.zentask.data.model.LoginResponse
import com.example.zentask.data.model.ResponseWithData
import com.example.zentask.data.model.SignInResult
import com.example.zentask.data.model.SignInState
import com.example.zentask.data.model.ValidationErrors
import com.example.zentask.data.repository.AuthRepository
import com.example.zentask.utils.validation.AuthValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    // Instance
    private val authUtils = AuthValidation()


    // State
    private val _loginRequest = MutableStateFlow(LoginRequest())
    private val _validationErrors = MutableStateFlow(mutableListOf<ValidationErrors>())
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    private val _signInState = MutableStateFlow(SignInState())

    // Getter
    val loginRequest: StateFlow<LoginRequest> get() = _loginRequest
    val validationErrors: StateFlow<List<ValidationErrors>> = _validationErrors
    val loginState: StateFlow<LoginState> = _loginState
    val signInState: StateFlow<SignInState> = _signInState

    // Actions to update fields
    fun updateLoginField(fieldName: String, value: String) {
        _loginRequest.update { currentRequest ->
            when (fieldName) {
                "email" -> currentRequest.copy(username = value)
                "password" -> currentRequest.copy(password = value)
                else -> currentRequest
            }
        }
        validateLoginRequest()
    }

    // Validate login request
    private fun validateLoginRequest() {
        _validationErrors.value = authUtils.validateLoginInput(_loginRequest).toMutableList()
    }

    // Perform login
    fun login() {
        viewModelScope.launch {
            // Reset the login state to loading
            _loginState.value = LoginState.Loading

            // Check if there are validation errors before making login request
            if (_validationErrors.value.isEmpty()) {
                val result = authRepository.login(_loginRequest.value)

                // Handle the result of the login attempt
                result.fold(
                    onSuccess = { loginResponse ->
                        _loginState.value = LoginState.Success(loginResponse)
                    },
                    onFailure = { error ->
                        _loginState.value = LoginState.Error(error.message ?: "Login failed")
                    }
                )
            } else {
                // If there are validation errors, we set the login state to idle and show the errors
                _loginState.value = LoginState.Error("Please correct validation errors")
            }
        }
    }

    fun signInWithGoogle() {

    }

    fun onSignInResult(result: SignInResult) {
        _signInState.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun resetState() {
        _signInState.update { SignInState() }
    }

}

// Possible states for the login process
sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val loginResponse: ResponseWithData<LoginResponse>?) : LoginState()
    data class Error(val message: String) : LoginState()
}
