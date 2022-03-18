package com.neowise.reactpizza.resources

import com.neowise.reactpizza.resources.request.AuthRequest
import com.neowise.reactpizza.resources.response.AuthResponse
import com.neowise.reactpizza.service.UserService
import com.neowise.reactpizza.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.PostMapping
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

    @PostMapping("login")
    fun login(@RequestBody body: AuthRequest): ResponseEntity<AuthResponse> {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(body.username, body.password))

            val user = userService.findByUsername(body.username)
                ?: throw UsernameNotFoundException("User with username: ${body.username} not found!")

            val token = jwtTokenProvider.createToken(body.username, user.roles)

            return ResponseEntity.ok(AuthResponse(user.username, token))
        }
        catch (e: AuthenticationException) {
            throw BadCredentialsException("Invalid username or password")
        }
    }
}

