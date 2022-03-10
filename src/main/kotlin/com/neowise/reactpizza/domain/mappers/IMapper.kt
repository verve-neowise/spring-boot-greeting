package com.neowise.reactpizza.domain.mappers

fun interface IMapper<T, R> {
    fun map(model: T) : R
}