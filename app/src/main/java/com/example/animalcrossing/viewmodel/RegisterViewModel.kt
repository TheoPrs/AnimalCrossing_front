package com.example.registerpage
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class RegisterState(
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)

class RegisterViewModel : ViewModel() {

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState : StateFlow<RegisterState> = _registerState

    fun register(surname: String, name: String, email: String, password: String, password2: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState(isLoading = true)

            // Simuler une requête réseau
            delay(2000)

            if (email.isNotEmpty() && password.isNotEmpty() && surname.isNotEmpty() && name.isNotEmpty() && password2.isNotEmpty()) {
                _registerState.value = RegisterState(isLoading = false)
            } else {
                _registerState.value = RegisterState(isLoading = false, errorMessage = "Veillez à bien remplir chaque champ")
            }
        }
    }
}