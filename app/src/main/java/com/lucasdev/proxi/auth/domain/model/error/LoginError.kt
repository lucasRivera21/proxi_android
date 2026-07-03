package com.lucasdev.proxi.auth.domain.model.error

sealed class LoginError

class LoginCredentialsError : LoginError()
class LoginUnknownError : LoginError()