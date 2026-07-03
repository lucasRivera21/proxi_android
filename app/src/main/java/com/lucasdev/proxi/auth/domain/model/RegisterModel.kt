package com.lucasdev.proxi.auth.domain.model

import com.lucasdev.proxi.auth.domain.model.error.RegisterError

data class RegisterModel(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isNameValid: Boolean = true,
    val isEmailValid: Boolean = true,
    val error: RegisterError? = null,
    val isPasswordValid: Boolean = true,
    val isLoading: Boolean = false
)
