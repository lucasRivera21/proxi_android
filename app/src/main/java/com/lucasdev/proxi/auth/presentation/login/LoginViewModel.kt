package com.lucasdev.proxi.auth.presentation.login

import androidx.lifecycle.ViewModel
import com.lucasdev.proxi.auth.domain.model.LoginModel
import com.lucasdev.proxi.core.isEmailValid
import com.lucasdev.proxi.core.isPasswordValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _loginModel = MutableStateFlow(LoginModel())
    val loginModel = _loginModel.asStateFlow()

    fun onEmailChange(value: String) {
        _loginModel.value = _loginModel.value.copy(email = value)
    }

    fun onPasswordChange(value: String) {
        _loginModel.value = _loginModel.value.copy(password = value)
    }

    fun onLoginClick() {
        _loginModel.value = _loginModel.value.copy(isLoading = true)
        if (!isFieldsValidate()) {
            _loginModel.value = _loginModel.value.copy(isLoading = false)
            return
        }
    }

    private fun isFieldsValidate(): Boolean {
        _loginModel.value = _loginModel.value.copy(
            isEmailValid = isEmailValid(_loginModel.value.email),
            isPasswordValid = isPasswordValid(_loginModel.value.password)
        )

        return _loginModel.value.isEmailValid && _loginModel.value.isPasswordValid
    }
}