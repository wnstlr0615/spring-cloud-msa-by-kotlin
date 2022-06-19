package com.example.orderservice.repository

import com.example.orderservice.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<OrderEntity, Long> {
    fun findAllByUserId(userId: String): List<OrderEntity>
}