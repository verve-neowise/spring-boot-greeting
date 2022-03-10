package com.neowise.reactpizza.data.model

import javax.persistence.*

@Entity(name = "Student")
@Table(name = "student")
open class StudentDto(
    @Column(name="first_name")
    open val firstName: String,
    @Column(name="last_name")
    open val lastName: String,
    @Column(name="email")
    open val email: String,
    @Column(name="age")
    open val age: Int
) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    open val id: Int = 0

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