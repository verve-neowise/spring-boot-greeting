package com.neowise.reactpizza.data.entity.pizza

import com.neowise.reactpizza.data.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "categories")
open class Category(

    @Column(name="name")
    open val name: String,

    @OneToMany
    open val pizzas: List<Pizza>

) : BaseEntity()