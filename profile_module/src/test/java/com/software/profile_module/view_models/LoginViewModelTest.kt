package com.software.profile_module.view_models

import com.software.profile_module.views.LoginIntent
import com.software.profile_module.views.LoginState
import com.software.profile_module_api.domain.EmailValidatorUseCase
import com.software.profile_module_api.domain.PasswordValidatorUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.TestDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LoginViewModelTest {
    private lateinit var viewModel: LoginViewModel
    private val emailValidatorUseCase = mock<EmailValidatorUseCase>()
    private val passwordValidatorUseCase = mock<PasswordValidatorUseCase>()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        viewModel = LoginViewModel(emailValidatorUseCase, passwordValidatorUseCase)
    }

    @Test
    fun `GIVEN a valid email WHEN isValidEmail intent is request THEN email value is valid`() =
        runTest {
            //Given
            whenever(emailValidatorUseCase("")).thenReturn(true)
            //When
            viewModel.userIntent.send(LoginIntent.IsValidEmail(""))
            //Then
            Assert.assertEquals(LoginState.IsValidEmail(true), viewModel.loginState.value)
        }

    @Test
    fun `GIVEN an invalid email WHEN isValidEmail intent is request THEN email value is invalid`() =
        runTest {
            //Given
            whenever(emailValidatorUseCase("")).thenReturn(false)
            //When
            viewModel.userIntent.send(LoginIntent.IsValidEmail(""))
            //Then
            Assert.assertEquals(LoginState.IsValidEmail(false), viewModel.loginState.value)
        }
}

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}