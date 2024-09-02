package com.example.zentask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zentask.data.model.LoginRequest
import com.example.zentask.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(authRepository: AuthRepository): ViewModel() {

    // Instance

    // State
    private val _loginRequest = MutableStateFlow<LoginRequest>(LoginRequest())

    // Getter
    val loginRequest: StateFlow<LoginRequest> get() = _loginRequest


    // Actions

    fun updateLoginField(fieldName:String, value: String){
        _loginRequest.update { currentRequest ->
            when(fieldName) {
                "email" -> currentRequest.copy(username = value as String)
                "password" -> currentRequest.copy(password = value as String)
                else -> currentRequest
            }
        }
    }

    fun login(){
        viewModelScope.launch {

        }
    }


}