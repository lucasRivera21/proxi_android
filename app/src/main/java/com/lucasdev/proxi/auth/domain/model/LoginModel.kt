package com.lucasdev.proxi.auth.domain.model

data class LoginModel(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true,
    val isLoading: Boolean = false,
)

