package com.software.profile_module.di

import com.software.profile_module.domain.EmailValidatorUseCaseImpl
import com.software.profile_module.domain.PasswordValidatorUseCaseImpl
import com.software.profile_module_api.domain.EmailValidatorUseCase
import com.software.profile_module_api.domain.PasswordValidatorUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileBinds {
    @Binds
    abstract fun bindEmailValidatorUseCase(emailValidatorUseCaseImpl: EmailValidatorUseCaseImpl): EmailValidatorUseCase

    @Binds
    abstract fun bindPasswordValidatorUseCase(passwordValidatorUseCaseImpl: PasswordValidatorUseCaseImpl): PasswordValidatorUseCase
}