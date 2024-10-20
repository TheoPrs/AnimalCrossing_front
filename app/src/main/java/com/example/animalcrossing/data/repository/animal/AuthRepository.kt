package com.example.app.data

import com.example.app.data.model.AuthResponse
import com.example.app.data.model.User
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*
import io.ktor.util.InternalAPI

class AuthRepository {
    private val supabaseUrl = "https://hkrzmotogcwjlwmdrmni.supabase.co"
    private val supabaseAnonKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imhrcnptb3RvZ2N3amx3bWRybW5pIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mjg2MzYwNDMsImV4cCI6MjA0NDIxMjA0M30.hHxxvNtHHgvFlXk9Dhdn27N7_5AZ8gBc0Cfe5D-4lZw" // Remplace par ta clé

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json() // Installe la sérialisation JSON
        }
        install(Logging) {
            level = LogLevel.ALL
        }
    }

    @OptIn(InternalAPI::class)
    suspend fun signUpWithEmail(email: String, password: String): AuthResponse {
        val response: HttpResponse = client.post("$supabaseUrl/auth/v1/signup") {
            contentType(ContentType.Application.Json)
            headers {
                append(HttpHeaders.Authorization, "Bearer $supabaseAnonKey")
            }
            body = mapOf(
                "email" to email,
                "password" to password
            )
        }
        return response.body() // Retourne l'objet AuthResponse
    }

    @OptIn(InternalAPI::class)
    suspend fun signInWithEmail(email: String, password: String): AuthResponse {
        val response: HttpResponse = client.post("$supabaseUrl/auth/v1/token?grant_type=password") {
            contentType(ContentType.Application.Json)
            headers {
                append(HttpHeaders.Authorization, "Bearer $supabaseAnonKey")
            }
            body = mapOf(
                "email" to email,
                "password" to password
            )
        }
        return response.body() // Retourne l'objet AuthResponse
    }
}