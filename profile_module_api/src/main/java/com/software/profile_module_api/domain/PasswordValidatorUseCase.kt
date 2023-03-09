package com.software.profile_module_api.domain

interface PasswordValidatorUseCase {
    suspend operator fun invoke(password: String): Boolean
}