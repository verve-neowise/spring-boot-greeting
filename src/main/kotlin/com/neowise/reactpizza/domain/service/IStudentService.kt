package com.neowise.reactpizza.domain.service

import com.neowise.reactpizza.data.model.StudentDto
import com.neowise.reactpizza.domain.model.Student

interface IStudentService {
    fun add(student: StudentDto): Student
    fun findAll(): List<Student>
    fun findById(id: Int): Student
}