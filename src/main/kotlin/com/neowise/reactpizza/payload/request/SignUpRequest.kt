package com.neowise.reactpizza.payload.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.neowise.reactpizza.data.entity.user.User

@JsonIgnoreProperties(ignoreUnknown = true)
data class SignUpRequest(
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
) {

    fun toUser() = User(
            username = username,
            password = password,
            email =  email,
            firstName = firstName,
            lastName = lastName,
            roles = emptyList()
        )
}