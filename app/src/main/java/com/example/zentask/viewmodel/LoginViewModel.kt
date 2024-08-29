package com.example.zentask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zentask.data.repository.AuthRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(authRepository: AuthRepository): ViewModel() {

//  Instance
//  State
//  Getter
//  Actions
    fun login(){
        viewModelScope.launch {

        }
    }


}