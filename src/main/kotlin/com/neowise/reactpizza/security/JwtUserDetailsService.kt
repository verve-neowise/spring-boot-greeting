package com.neowise.reactpizza.security

import com.neowise.reactpizza.data.entity.User
import com.neowise.reactpizza.service.UserService
import com.neowise.reactpizza.security.jwt.JwtUserFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JwtUserDetailsService @Autowired constructor(
        private val userService: UserService
        ) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {

        val user: User = userService.findByUsername(username)
            ?: throw UsernameNotFoundException("user with username: $username not found.")

        return JwtUserFactory.create(user)
    }
}