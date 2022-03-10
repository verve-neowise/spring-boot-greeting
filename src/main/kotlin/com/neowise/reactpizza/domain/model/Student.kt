package com.neowise.reactpizza.domain.model

class Student(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int
) {
    override fun toString(): String {
        return "Student(" +
                "id=$id, " +
                "firstName='$firstName', " +
                "lastName='$lastName', " +
                "email='$email', " +
                "age=$age" +
                ")"
    }
}