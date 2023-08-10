package com.software.profile_module.views

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class IsValidEmail(val isValid: Boolean) : LoginState()
    data class IsValidPassword(val isValid: Boolean) : LoginState()
    data class Error(val error: String?) : LoginState()
}
