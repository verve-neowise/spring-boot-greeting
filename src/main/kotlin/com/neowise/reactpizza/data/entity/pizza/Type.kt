package com.neowise.reactpizza.data.entity.pizza

import com.neowise.reactpizza.data.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "types")
open class Type(
    @Column(name = "name")
    open val name: String,
    @ManyToMany(mappedBy = "types")
    open val pizzas: List<Pizza>
): BaseEntity()