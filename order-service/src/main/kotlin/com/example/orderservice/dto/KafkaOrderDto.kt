package com.example.orderservice.dto

data class KafkaOrderDto(
    val schema: Schema,
    val payload: Payload
)

data class Payload(
    val order_id: String,
    val user_id: String,
    val product_id: String,
    val qty: Int,
    val unit_price: Int,
    val total_price: Int
)
data class Schema(
    val type: String,
    val fields: List<Field>,
    val optional: Boolean,
    val name: String
)

data class Field(
    val type: String,
    val optional: Boolean,
    val field: String
)
