package com.neowise.reactpizza.data.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.util.*
import javax.persistence.*

@MappedSuperclass
open class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long = 0

    @CreatedDate
    @Column(name = "create_at")
    open val createAt: Date = Date()

    @LastModifiedDate
    @Column(name = "update_at")
    open val updateAt: Date = Date()

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    open val status: Status = Status.ACTIVE
}