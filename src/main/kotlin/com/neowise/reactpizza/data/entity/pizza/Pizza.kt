package com.neowise.reactpizza.data.entity.pizza

import com.neowise.reactpizza.data.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "pizzas")
class Pizza(

    @Column(name = "imageUrl")
    val imageUrl: String,

    @ManyToMany
    @JoinTable(name = "pizza_types",
        joinColumns = [JoinColumn(name = "pizza_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "type_id", referencedColumnName = "id")]
    )
    val types: List<Type>,

    @ManyToMany
    @JoinTable(name = "pizza_sizes",
        joinColumns = [JoinColumn(name = "pizza_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "size_id", referencedColumnName = "id")]
    )
    val sizes: List<Size>,

    @Column(name = "price")
    val price: Double,

    @ManyToOne
    @JoinTable(name = "pizza_category",
        joinColumns = [JoinColumn(name = "pizza_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "category_id", referencedColumnName = "id")]
    )
    val category: Category,

    @Column(name = "rating")
    val rating: Double
): BaseEntity()