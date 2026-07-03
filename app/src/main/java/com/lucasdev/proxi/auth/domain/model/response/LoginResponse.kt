package com.lucasdev.proxi.auth.domain.model.response

sealed class LoginResponse

class LoginResponseSuccess : LoginResponse()
class LoginResponseCredentialsError : LoginResponse()
class LoginResponseUnknowError(val errorMessage: String? = null) : LoginResponse()