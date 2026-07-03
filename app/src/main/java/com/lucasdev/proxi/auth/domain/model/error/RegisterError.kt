package com.lucasdev.proxi.auth.domain.model.error

sealed class RegisterError

class RegisterEmailAlreadyExist : RegisterError()
class RegisterUnknownError : RegisterError()