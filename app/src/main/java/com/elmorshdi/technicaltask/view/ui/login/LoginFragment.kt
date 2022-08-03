package com.elmorshdi.technicaltask.view.ui.login

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.elmorshdi.technicaltask.R
import com.elmorshdi.technicaltask.databinding.FragmentHomeBinding
import com.elmorshdi.technicaltask.databinding.FragmentLoginBinding
import com.elmorshdi.technicaltask.view.util.SharedPreferencesManager
import com.elmorshdi.technicaltask.view.util.SharedPreferencesManager.getLoginValue
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val logged = getLoginValue(sharedPreferences)
        if (logged) {
            navigateToMain()
        }
        binding.loginButton.setOnClickListener {
            val phone = binding.loginEmailEditText.text?.trim().toString()
            val password = binding.loginPasswordEditText.text?.trim().toString()
            viewModel.login(phone, password)
        }
        lifecycleScope.launchWhenStarted {
            viewModel.loginUiState.collect { event ->
                when (event) {
                    is LoginViewModel.LoginUiState.Success -> {
                        binding.loginSpinKit.isVisible = false
                        SharedPreferencesManager.signInShared(
                            sharedPreferences.edit(),
                            event.token)
                        navigateToMain()
                    }
                    is LoginViewModel.LoginUiState.Error -> {
                        clearError()
                        binding.loginSpinKit.isVisible = false
                        when (event.error) {

                            is LoginViewModel.Error.PasswordNotValid -> {
                                binding.loginPasswordTextField.error=getString(R.string.Invalid_password)
                               }
                            is LoginViewModel.Error.EmailNotValid -> {
                                binding.loginEmailTextField.error =
                                    getString(R.string.enter_valid_number)
                            }
                            else -> Unit
                        }

                    }
                    LoginViewModel.LoginUiState.Loading -> {
                        clearError()
                        binding.loginSpinKit.isVisible = true
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun clearError() {
        binding.loginEmailTextField.isErrorEnabled = false
        binding.loginPasswordTextField.isErrorEnabled = false

    }

    private fun navigateToMain() {
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        binding.root.findNavController().navigate(action)
    }

}