package com.neowise.reactpizza.resources.request

data class AuthenticationRequest(
    val username: String,
    val password: String
)