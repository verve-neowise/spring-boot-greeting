package com.neowise.reactpizza.service

import com.neowise.reactpizza.data.entity.user.User
import java.util.*

interface UserService {

    fun register(user: User): User

    fun getAll(): List<User>

    fun findByUsername(username: String): Optional<User>

    fun findById(id: Long): User

    fun delete(id: Long)
}