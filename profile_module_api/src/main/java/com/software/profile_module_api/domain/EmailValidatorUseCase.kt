package com.software.profile_module_api.domain

interface EmailValidatorUseCase {
    suspend operator fun invoke(email: String)
}