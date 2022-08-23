package com.neowise.reactpizza.data.repository

import com.neowise.reactpizza.data.entity.pizza.Size
import org.springframework.data.jpa.repository.JpaRepository

interface PizzaSizeRepository : JpaRepository<Size, Long>