package com.example.orderservice.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "orders")
class OrderEntity(
    @Column(nullable = false)
    val productId: String,
    @Column(nullable = false)
    var qty: Int,
    @Column(nullable = false)
    var unitPrice: Int,
    @Column(nullable = false)
    var totalPrice: Int,
    @Column(nullable = false)
    val userId: String,
    @Column(nullable = false)
    val orderId: String,
    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
