package com.neowise.reactpizza.security.jwt

import com.neowise.reactpizza.data.entity.Role
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest


@Component
class JwtTokenProvider
    @Autowired constructor(
        private val userDetailsService: UserDetailsService
    ){

    @Value(value = "{jwt.token.secret}")
    private lateinit var secret: String

    @Value(value = "{jwt.token.expired}")
    private var validityInMilliseconds: Long = 0

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @PostConstruct
    protected fun init() {
        secret = Base64.getEncoder().encodeToString(secret.toByteArray())
    }

    fun createToken(username: String, roles: List<Role>): String {
        val claims: Claims = Jwts.claims().setSubject(username)
        claims["roles"] = getRoleNames(roles)

        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)

        return Jwts.builder() //
            .setClaims(claims) //
            .setIssuedAt(now) //
            .setExpiration(validity) //
            .signWith(SignatureAlgorithm.HS256, secret) //
            .compact()
    }

    fun authentication(token: String) : Authentication? {
        val userDetails = userDetailsService.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUsername(token: String) : String {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body
            .subject
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader("Authorization")

        return if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
           bearerToken.substring(7..bearerToken.length)
        }
        else {
            null
        }
    }

    fun validateToken(token: String) : Boolean {
        return try {
            Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .body
                .expiration
                .before(Date())
                .not()
        }
        catch (e: JwtException) {
            throw JwtAuthenticationException("JWT token is expired or invalid")
        }
        catch (e: IllegalArgumentException) {
            throw JwtAuthenticationException("JWT token is expired or invalid")
        }
    }

    fun getRoleNames(roles: List<Role>): List<String> {
        return roles.map { it.name }
    }
}