package com.example.orderservice.controller

import com.example.orderservice.dto.OrderRequest
import com.example.orderservice.dto.OrderResponse
import com.example.orderservice.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order-service/{userId}/orders")
class OrderController(
    val orderService: OrderService
) {
    @PostMapping
    fun orderAdd(
        @PathVariable userId: String,
        @RequestBody request: OrderRequest
    ): ResponseEntity<OrderResponse> {
        val orderResponse = orderService.addOrder(userId, request)
        return ResponseEntity.ok(orderResponse)
    }

    @GetMapping
    fun findOrderByUserId(
        @PathVariable userId: String
    ): ResponseEntity<List<OrderResponse>> {
        val orderResponse: List<OrderResponse> = orderService.findOrderByUserId(userId)
        return ResponseEntity.ok(orderResponse)
    }
}