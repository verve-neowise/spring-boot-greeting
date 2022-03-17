package com.neowise.reactpizza.security.jwt

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class JwtUser(
    @JsonIgnore
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    @JsonIgnore
    val lastPasswordResetDate: Date,
    private val username: String,
    private val password: String,
    private val enabled: Boolean,
    private val authorities: MutableCollection<out GrantedAuthority>
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    @JsonIgnore
    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    @JsonIgnore
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return enabled
    }
}