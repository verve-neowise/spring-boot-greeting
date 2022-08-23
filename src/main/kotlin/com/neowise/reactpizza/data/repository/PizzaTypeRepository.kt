package com.neowise.reactpizza.data.repository

import com.neowise.reactpizza.data.entity.pizza.Type
import org.springframework.data.jpa.repository.JpaRepository

interface PizzaTypeRepository : JpaRepository<Type, Long>