package com.example.loginpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LoginState(
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)

class LoginViewModel : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState(isLoading = true)

            // Simuler une requête réseau
            delay(2000)

            // Validation simple (email et password non vides)
            if (email.isNotEmpty() && password.isNotEmpty()) {
                _loginState.value = LoginState(isLoading = false)
            } else {
                _loginState.value = LoginState(isLoading = false, errorMessage = "Invalid email or password")
            }
        }
    }
}