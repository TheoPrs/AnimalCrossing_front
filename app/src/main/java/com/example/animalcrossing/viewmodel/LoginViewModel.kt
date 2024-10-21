package com.example.loginpage

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.app.data.AuthRepository
import com.example.app.data.model.AuthResponse // Assure-toi d'importer le bon modèle
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class LoginState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val authResponse: AuthResponse? = null // Ajouter un champ pour stocker la réponse d'authentification
)

class LoginViewModel : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState
    //private val authRepository = AuthRepository() // Instance de AuthRepository

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState(isLoading = true)

            try {
                if (email == "test@gmail.com" && password == "1234azer") {
                    _loginState.value = LoginState(isLoading = false)

                }

            } catch (e: Exception) {
                _loginState.value =
                    LoginState(isLoading = false, errorMessage = e.message ?: "Unknown error")
            }
        }
    }
}