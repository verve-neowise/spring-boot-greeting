package com.neowise.reactpizza.security

import com.neowise.reactpizza.data.entity.User
import com.neowise.reactpizza.domain.UserService
import com.neowise.reactpizza.security.jwt.JwtUserFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
class JwtUserDetailsService
    @Autowired constructor(
        private val userService: UserService
    ) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userService.findByUsername(username)
        return JwtUserFactory.create(user)
    }
}