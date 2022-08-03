package com.elmorshdi.technicaltask.view.ui.login

import androidx.lifecycle.ViewModel
import com.elmorshdi.technicaltask.core.repository.Repository
import com.elmorshdi.technicaltask.view.util.isEmailValid
import com.elmorshdi.technicaltask.view.util.isValidNumber
import com.elmorshdi.technicaltask.view.util.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState
    fun login(email: String, password: String) {
        _loginUiState.value = LoginUiState.Loading
        when {
            !email.isValidNumber()|| email.isEmpty() -> {
                _loginUiState.value = LoginUiState.Error(Error.EmailNotValid)
            }
            !password.isValidPassword() || password.isEmpty() -> {
                _loginUiState.value = LoginUiState.Error(Error.PasswordNotValid)
            }
            else -> {

                _loginUiState.value = LoginUiState.Success("token")


            }
        }
    }

    sealed class LoginUiState {
        data class Success(val token: String) : LoginUiState()
        data class Error(val error: LoginViewModel.Error) : LoginUiState()
        object Loading : LoginUiState()
        object Empty : LoginUiState()
    }

    sealed class Error {
        object PasswordNotValid : Error()
        object EmailNotValid : Error()
    }


}