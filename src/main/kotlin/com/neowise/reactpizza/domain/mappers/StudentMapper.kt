package com.neowise.reactpizza.domain.mappers

import com.neowise.reactpizza.data.model.StudentDto
import com.neowise.reactpizza.domain.model.Student


fun StudentDto.toStudent(): Student {
    return Student(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        age = this.age
    )
}

fun Student.toDto(): StudentDto {
    return StudentDto(
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        age = this.age
    )
}