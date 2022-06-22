package com.example.orderservice.controller

import com.example.orderservice.dto.OrderRequest
import com.example.orderservice.dto.OrderResponse
import com.example.orderservice.messagequeue.KafkaProducer
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
    val orderService: OrderService,
    val kafkaProducer: KafkaProducer
) {
    @PostMapping
    fun orderAdd(
        @PathVariable userId: String,
        @RequestBody request: OrderRequest
    ): ResponseEntity<OrderResponse> {
        /*기존 서비스 작업*/
        val orderResponse = orderService.addOrder(userId, request)

        /*카프카 template 요청*/
        kafkaProducer.send("example-catalog-topic", request)
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