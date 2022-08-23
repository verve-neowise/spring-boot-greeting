package com.neowise.reactpizza.data.repository

import com.neowise.reactpizza.data.entity.user.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {

    fun findByName(name: String): Role
}