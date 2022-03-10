package com.neowise.reactpizza.data.repository

import com.neowise.reactpizza.data.model.StudentDto
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<StudentDto, Int> {

}