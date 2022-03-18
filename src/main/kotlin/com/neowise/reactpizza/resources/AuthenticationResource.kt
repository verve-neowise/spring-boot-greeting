package com.neowise.reactpizza.resources

import com.neowise.reactpizza.data.entity.User
import com.neowise.reactpizza.resources.request.AuthenticationRequest
import com.neowise.reactpizza.resources.response.AuthenticationResponse
import com.neowise.reactpizza.service.UserService
import com.neowise.reactpizza.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationResource @Autowired constructor(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenProvider: JwtTokenProvider,
    private val userService: UserService
) {

    fun login(@RequestBody body: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(body.username, body.password))

            val user = userService.findByUsername(body.username)
                ?: throw UsernameNotFoundException("User with username: ${body.username} not found!")

            val token = jwtTokenProvider.createToken(body.username, user.roles)

            return ResponseEntity.ok(AuthenticationResponse(user.username, token))
        }
        catch (e: AuthenticationException) {
            throw BadCredentialsException("Invalid username or password")
        }
    }
}

