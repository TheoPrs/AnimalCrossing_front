package com.example.loginpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.AuthRepository
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
    private val authRepository = AuthRepository() // Instance de AuthRepository

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState(isLoading = true)

            try {
                // Appeler la méthode d'authentification
                val authResponse: AuthResponse = authRepository.signInWithEmail(email, password)

                // Si la connexion est réussie, mettre à jour l'état
                _loginState.value = LoginState(isLoading = false, authResponse = authResponse)
            } catch (e: Exception) {
                // Gérer les erreurs ici
                _loginState.value = LoginState(isLoading = false, errorMessage = e.message ?: "Unknown error")
            }
        }
    }
}