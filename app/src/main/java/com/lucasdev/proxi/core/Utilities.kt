package com.lucasdev.proxi.core

import android.util.Patterns

fun isNameValid(name: String) = name.length >= 4

fun isEmailValid(email: String) =
    email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()

fun isPasswordValid(password: String) = password.length >= 8