package com.neowise.reactpizza.services

import com.neowise.reactpizza.data.model.StudentDto
import com.neowise.reactpizza.data.repository.StudentRepository
import com.neowise.reactpizza.domain.mappers.toStudent
import com.neowise.reactpizza.domain.model.Student
import com.neowise.reactpizza.domain.service.IStudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudentService : IStudentService {

    @Autowired
    private lateinit var studentRepository: StudentRepository

    override fun add(student: StudentDto): Student {
        return studentRepository.save(student).toStudent()
    }

    override fun findAll(): List<Student> {
        return studentRepository.findAll().map { it.toStudent() }
    }

    override fun findById(id: Int) : Student {
        return studentRepository.getById(id).toStudent()
    }
}