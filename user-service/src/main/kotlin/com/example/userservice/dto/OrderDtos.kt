package com.example.userservice.dto

import java.time.LocalDateTime
data class OrderResponse(
    val product: String,
    val qty: Int,
    val unitPrice: Int,
    val totalPrice: Int,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val orderId: String
)
