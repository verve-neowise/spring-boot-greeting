package com.neowise.reactpizza.data.entity

import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role(
    @Column(name="name")
    val name: String,

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    val users: List<User>
) : BaseEntity()