package com.neowise.reactpizza.data.entity

import javax.persistence.*

@Entity
@Table(name="users")
data class User(

    @Column(name = "username")
    val username: String,

    @Column(name = "first_name")
    val firstName: String,

    @Column(name = "last_name")
    val lastName: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

//    @Column(name = "roles")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    val roles: List<Role>

): BaseEntity()