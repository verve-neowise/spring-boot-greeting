package com.neowise.reactpizza.data.entity.user

import com.neowise.reactpizza.data.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "roles")
class Role(
    @Column(name="name")
    val name: String,

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    val users: List<User>

) : BaseEntity()