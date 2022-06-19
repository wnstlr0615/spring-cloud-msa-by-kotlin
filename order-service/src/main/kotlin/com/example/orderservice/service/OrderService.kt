package com.example.orderservice.service

import com.example.orderservice.dto.OrderRequest
import com.example.orderservice.dto.OrderResponse

interface OrderService {
    fun AddOrder(userId: String, request: OrderRequest): OrderResponse

}
