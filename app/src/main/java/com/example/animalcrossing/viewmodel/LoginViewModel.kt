package com.example.loginpage
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.InternalAPI

data class LoginState(
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)

class LoginViewModel : ViewModel() {
    private val client = HttpClient(CIO) {
        install(DefaultRequest) {
            header(HttpHeaders.Accept, ContentType.Application.Json)
        }
    }

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    @OptIn(InternalAPI::class)
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState(isLoading = true)
            try {
                val response: HttpResponse = client.post("https://xyzcompany.supabase.co/auth/v1/token") {
                    contentType(ContentType.Application.Json)
                    body = """{"email": "$email", "password": "$password", "grant_type": "password"}"""
                }

                if (response.status == HttpStatusCode.OK) {
                    //ifConnected
                    _loginState.value = LoginState()
                    // Gérer la navigation ou la logique après la connexion ici
                } else {
                    _loginState.value = LoginState(errorMessage = "Erreur de connexion")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState(errorMessage = "Erreur : ${e.localizedMessage}")
            }
        }
    }
}