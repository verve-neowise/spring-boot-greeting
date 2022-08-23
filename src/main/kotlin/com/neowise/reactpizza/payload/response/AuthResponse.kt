package com.neowise.reactpizza.payload.response

data class AuthResponse(
    val username: String,
    val token: String,
    val message: String = ""
)