package com.neowise.reactpizza.data.entity

import com.neowise.reactpizza.domain.model.Status
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.util.*
import javax.persistence.*

@MappedSuperclass
open class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @CreatedDate
    @Column(name = "create_at")
    val createAt: Date = Date()

    @LastModifiedDate
    @Column(name = "update_at")
    val updateAt: Date = Date()

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    val status: Status = Status.ACTIVE
}