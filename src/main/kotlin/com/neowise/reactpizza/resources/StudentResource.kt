package com.neowise.reactpizza.resources

import com.neowise.reactpizza.domain.mappers.toDto
import com.neowise.reactpizza.domain.model.Student
import com.neowise.reactpizza.services.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StudentResource {

    @Autowired
    private lateinit var studentService: StudentService

    @GetMapping("/")
    fun all(): List<Student> {
        return studentService.findAll()
    }

    @GetMapping("/{id}")
    fun byId(@PathVariable id: Int): Student {
        return studentService.findById(id)
    }

    @PostMapping("/")
    fun add(student: Student) {
        studentService.add(student.toDto())
    }
}

