package com.example.orderservice.service

import com.example.orderservice.dto.OrderRequest
import com.example.orderservice.dto.OrderResponse
import com.example.orderservice.entity.OrderEntity
import com.example.orderservice.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class OrderServiceImpl(
    val orderRepository: OrderRepository
) : OrderService {
    @Transactional
    override fun addOrder(userId: String, request: OrderRequest): OrderResponse {
        val orderEntity = orderRepository.save(
            request.let {
                OrderEntity(
                    it.productId,
                    it.qty,
                    it.unitPrice,
                    it.totalPrice,
                    userId,
                    UUID.randomUUID().toString()
                )
            }
        )
        return OrderResponse(orderEntity)
    }

    override fun findOrderByUserId(userId: String): List<OrderResponse> {
        val orderEntity = orderRepository.findAllByUserId(userId)
        return orderEntity.map(::OrderResponse)
    }
}