package com.software.profile_module.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.software.profile_module.databinding.FragmentLoginBinding
import com.software.profile_module.view_models.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUpObserve()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initView() = with(binding) {
        emailText.doOnTextChanged { text, _, _, _ ->
            lifecycleScope.launch {
                viewModel.userIntent.send(LoginIntent.IsValidEmail(text.toString()))
            }
        }

        passwordText.doOnTextChanged { text, _, _, _ ->
            lifecycleScope.launch {
                viewModel.userIntent.send(LoginIntent.IsValidPassword(text.toString()))
            }
        }
    }

    private fun setUpObserve() {
        lifecycleScope.launch {
            viewModel.loginState.collect { loginState ->
                when (loginState) {
                    is LoginState.Loading -> {}
                    is LoginState.Error -> {}
                    is LoginState.IsValidEmail -> {
                        with(binding.email) {
                            error = if (loginState.isValid) "" else "Error"
                            isErrorEnabled = !loginState.isValid
                        }
                    }
                    is LoginState.IsValidPassword -> {
                        with(binding.password) {
                            error = if (loginState.isValid) "" else "Error"
                            isErrorEnabled = !loginState.isValid
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}