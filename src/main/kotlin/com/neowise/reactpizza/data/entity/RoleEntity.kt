package com.neowise.reactpizza.data.entity

import javax.persistence.*

@Entity
@Table(name = "roles")
data class RoleEntity(
    @Column(name="name")
    val name: String,

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    val users: List<UserEntity>
) : BaseEntity()