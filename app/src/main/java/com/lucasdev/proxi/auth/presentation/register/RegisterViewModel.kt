package com.lucasdev.proxi.auth.presentation.register

import androidx.lifecycle.ViewModel
import com.lucasdev.proxi.auth.domain.model.RegisterModel
import com.lucasdev.proxi.core.isEmailValid
import com.lucasdev.proxi.core.isNameValid
import com.lucasdev.proxi.core.isPasswordValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    private val _registerModel = MutableStateFlow(RegisterModel())
    val registerModel = _registerModel.asStateFlow()

    fun onNameChange(value: String) {
        _registerModel.value = _registerModel.value.copy(name = value, isNameValid = true)
    }

    fun onEmailChange(value: String) {
        _registerModel.value = _registerModel.value.copy(email = value, isEmailValid = true)
    }

    fun onPasswordChange(value: String) {
        _registerModel.value = _registerModel.value.copy(password = value, isPasswordValid = true)
    }

    fun onCreateAccount() {
        _registerModel.value = _registerModel.value.copy(isLoading = true)
        if (onValidateFields()) {
            _registerModel.value = _registerModel.value.copy(isLoading = false)
            return
        }
    }

    private fun onValidateFields(): Boolean {
        _registerModel.value = _registerModel.value.copy(
            isNameValid = isNameValid(_registerModel.value.name),
            isEmailValid = isEmailValid(_registerModel.value.email),
            isPasswordValid = isPasswordValid(_registerModel.value.password)
        )

        return _registerModel.value.isNameValid && _registerModel.value.isEmailValid && _registerModel.value.isPasswordValid
    }
}