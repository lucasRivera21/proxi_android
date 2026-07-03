package com.lucasdev.proxi.auth.domain.model.response

sealed class CreateUserWithEmailResponse {
}

class CreateUserWithEmailResponseSuccess : CreateUserWithEmailResponse()
class EmailAlreadyExistError : CreateUserWithEmailResponse()
class CreateUserWithEmailUnknownError(val errorMessage: String? = null) :
    CreateUserWithEmailResponse()