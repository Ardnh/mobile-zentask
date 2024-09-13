package com.example.zentask.utils.validation

import com.example.zentask.data.model.LoginRequest
import com.example.zentask.data.model.RegisterRequest
import com.example.zentask.data.model.ValidationErrors
import kotlinx.coroutines.flow.MutableStateFlow

class AuthValidation {

    // Fungsi ekstensi untuk validasi input
    private fun String?.isValidInput(): Boolean {
        return !this.isNullOrBlank()
    }
    private fun validatePassword(password: String): Boolean {
        return password.length >= 5
    }

    // Fungsi validasi untuk login
    fun validateLoginInput(input: MutableStateFlow<LoginRequest>): List<ValidationErrors> {
        val errorsValidation = mutableListOf<ValidationErrors>()

        val validations = listOf(
            Triple("username", !input.value.username.isValidInput(), "Username cannot be empty"),
            Triple("password", !input.value.password.isValidInput(), "Password cannot be empty"),
            Triple("password", !validatePassword(if(input.value.password.isValidInput()) input.value.password else ""), "Password must be at least 5 characters long.")

        )

        validations.forEach { (field, isError, errorMsg) ->
            val existingError = errorsValidation.find { it.field == field }
            if (isError) {
                if(existingError != null) {
                    if(!existingError.errors.contains(errorMsg)){
                        existingError.errors.add(errorMsg)
                    }
                } else {
                    errorsValidation.add(ValidationErrors(field = field, errors = mutableListOf(errorMsg)))
                }
            } else {
                if (existingError != null) {
                    existingError.errors.remove(errorMsg)
                    if(existingError.errors.isEmpty()){
                        errorsValidation.remove(existingError)
                    }
                }
            }
        }

        return errorsValidation
    }

    // Fungsi validasi untuk registrasi
    fun validateRegisterInput(input: MutableStateFlow<RegisterRequest>): List<ValidationErrors> {
        val errorsValidation = mutableListOf<ValidationErrors>()

        val validations = listOf(
            Triple("username", !input.value.username.isValidInput(), "Username cannot be empty"),
            Triple("email", !input.value.email.isValidInput(), "Email cannot be empty"),
            Triple("email", !android.util.Patterns.EMAIL_ADDRESS.matcher(input.value.email).matches(), "Invalid email format"),
            Triple("password", input.value.password.isBlank(), "Password cannot be empty"),
            Triple("password", input.value.password.isNotBlank() && !validatePassword(input.value.password), "Password must be at least 5 characters long")
        )

        validations.forEach { (field, isError, errorMsg) ->
            val existingError = errorsValidation.find { it.field == field }
            if (isError) {
                if (existingError != null) {
                    if (!existingError.errors.contains(errorMsg)) {
                        existingError.errors.add(errorMsg)
                    }
                } else {
                    errorsValidation.add(ValidationErrors(field = field, errors = mutableListOf(errorMsg)))
                }
            } else {
                if (existingError != null) {
                    existingError.errors.remove(errorMsg)
                    if (existingError.errors.isEmpty()) {
                        errorsValidation.remove(existingError)
                    }
                }
            }
        }

        return errorsValidation
    }




}