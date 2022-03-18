package com.neowise.reactpizza.service.impl

import com.neowise.reactpizza.data.entity.User
import com.neowise.reactpizza.data.repository.RoleRepository
import com.neowise.reactpizza.data.repository.UserRepository
import com.neowise.reactpizza.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl @Autowired constructor(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) : UserService {


    override fun register(user: User): User {
        val roleUser = roleRepository.findByName("ROLE_USER")
        val newUser = user.copy(
            password = passwordEncoder.encode(user.password),
            roles = listOf(roleUser)
        )
        return userRepository.save(newUser)
    }

    override fun getAll(): List<User> {
        return userRepository.findAll()
    }

    override fun findByUsername(username: String): User {
        return userRepository.findByUsername(username)
    }

    override fun findById(id: Long): User {
        return userRepository.findById(id).orElseThrow {
            UsernameNotFoundException("user with id $id not found")
        }
    }

    override fun delete(id: Long) {
        userRepository.deleteById(id)
    }
}