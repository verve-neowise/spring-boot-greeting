package com.neowise.reactpizza.data.repository

import com.neowise.reactpizza.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username: String): User
}