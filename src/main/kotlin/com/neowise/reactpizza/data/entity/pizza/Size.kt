package com.neowise.reactpizza.data.entity.pizza

import com.neowise.reactpizza.data.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "sizes")
open class Size(
    @Column(name = "size")
    open val size: Int,
    @ManyToMany(mappedBy = "sizes")
    open val pizzas: List<Pizza>
): BaseEntity()