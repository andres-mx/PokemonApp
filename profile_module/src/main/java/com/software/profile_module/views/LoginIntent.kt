package com.software.profile_module.views

sealed class LoginIntent {
    data class IsValidEmail(val email: String) : LoginIntent()
    data class IsValidPassword(val password: String) : LoginIntent()
}
