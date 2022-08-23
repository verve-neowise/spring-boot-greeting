package com.neowise.reactpizza.resources

import com.neowise.reactpizza.payload.request.AuthRequest
import com.neowise.reactpizza.payload.request.SignUpRequest
import com.neowise.reactpizza.payload.response.AuthResponse
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
        return try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(body.username, body.password))

            val user = userService
                .findByUsername(body.username)
                .orElseThrow {
                    UsernameNotFoundException("User with username: ${body.username} not found!")
                }

            val token = jwtTokenProvider.createToken(body.username, user.roles)

            ResponseEntity.ok(AuthResponse(user.username, token))
        }
        catch (e: AuthenticationException) {
            ResponseEntity
                .badRequest()
                .body(AuthResponse("", "", e.message!!))
        }
    }

    @PostMapping("signup")
    fun signup(@RequestBody body: SignUpRequest): ResponseEntity<AuthResponse> {

        return try {
            userService
                .findByUsername(body.username)
                .ifPresent {
                    throw BadCredentialsException("Username ${body.username} already taken.")
                }

            val user = userService.register(body.toUser())
            val token = jwtTokenProvider.createToken(body.username, user.roles)

            ResponseEntity.ok(AuthResponse(user.username, token))
        }
        catch (e: AuthenticationException) {
            ResponseEntity
                .badRequest()
                .body(AuthResponse("", "", e.message!!))
        }
    }
}