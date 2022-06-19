package com.example.orderservice.dto

import com.example.orderservice.entity.OrderEntity
import java.time.LocalDateTime

data class OrderRequest(
    val productId: String,
    val qty: Int,
    val unitPrice: Int
) {
    val totalPrice = qty * unitPrice
}

data class OrderResponse(
    val productId: String,
    val qty: Int,
    val unitPrice: Int,
    val totalPrice: Int,
    val createdAt: LocalDateTime,
    val orderId: String
) {
    constructor(order: OrderEntity) : this(
        order.productId,
        order.qty,
        order.unitPrice,
        order.totalPrice,
        order.createdAt,
        order.orderId
    )
}
