package com.example.orderservice.controller

import com.example.orderservice.dto.OrderDto
import com.example.orderservice.dto.OrderRequest
import com.example.orderservice.dto.OrderResponse
import com.example.orderservice.messagequeue.KafkaProducer
import com.example.orderservice.messagequeue.OrderProducer
import com.example.orderservice.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/order-service/{userId}/orders")
class OrderController(
    val orderService: OrderService,
    val kafkaProducer: KafkaProducer,
    val orderProducer: OrderProducer
) {
    @PostMapping
    fun orderAdd(
        @PathVariable userId: String,
        @RequestBody request: OrderRequest
    ): ResponseEntity<OrderDto> {
        /*기존 서비스 작업*/
        //val orderResponse = orderService.addOrder(userId, request)

        /*카프카를 통해 메시지 전달(Order 정보 전달)*/
        val orderDto = request.let {
            OrderDto(
                it.productId,
                userId,
                UUID.randomUUID().toString(),
                it.qty,
                it.unitPrice,
                it.totalPrice
            )
        }

        /*카프카 template 요청(Catalog 서버로 정보 전단)*/
        kafkaProducer.send("example-catalog-topic", request)
        orderProducer.send("orders", orderDto)
        return ResponseEntity.ok(orderDto)
    }

    @GetMapping
    fun findOrderByUserId(
        @PathVariable userId: String
    ): ResponseEntity<List<OrderResponse>> {
        val orderResponse: List<OrderResponse> = orderService.findOrderByUserId(userId)
        return ResponseEntity.ok(orderResponse)
    }
}