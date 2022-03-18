package com.neowise.reactpizza.security.jwt

import com.neowise.reactpizza.data.entity.Role
import com.neowise.reactpizza.data.entity.User
import com.neowise.reactpizza.data.entity.Status
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority


class JwtUserFactory {
    companion object {

        fun create(user: User): JwtUser {
            return JwtUser(
                id = user.id,
                username = user.username,
                firstName = user.firstName,
                lastName = user.lastName,
                email = user.email,
                password = user.password,
                authorities = user.roles.toGrantedAuthorities(),
                enabled = user.status == Status.ACTIVE,
                lastPasswordResetDate = user.updateAt
            )
        }

        private fun List<Role>.toGrantedAuthorities(): MutableCollection<GrantedAuthority> {
            return this.map { SimpleGrantedAuthority(it.name) }.toMutableList()
        }
    }
}