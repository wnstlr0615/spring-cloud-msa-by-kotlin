package com.example.orderservice.service

import com.example.orderservice.dto.OrderRequest
import com.example.orderservice.dto.OrderResponse

interface OrderService {
    fun addOrder(userId: String, request: OrderRequest): OrderResponse
    fun findOrderByUserId(userId: String): List<OrderResponse>

}
