package com.example.orderservice.messagequeue


import com.example.orderservice.dto.Field
import com.example.orderservice.dto.KafkaOrderDto
import com.example.orderservice.dto.OrderDto
import com.example.orderservice.dto.Payload
import com.example.orderservice.dto.Schema
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class OrderProducer(
    val kafkaTemplate: KafkaTemplate<String , String>
) {
    private val fields = listOf(
        Field("string", true, "order_id"),
        Field("string", true, "user_id"),
        Field("string", true, "product_id"),
        Field("int32", true, "qty"),
        Field("int32", true, "unit_price"),
        Field("int32", true, "total_price"),
    )
    val schema = Schema(type = "struct", fields = fields, optional = false, name = "orders")

    companion object {
        private val log = LoggerFactory.getLogger(OrderProducer::class.java)
    }
    fun send(topic: String, orderDto: OrderDto): OrderDto {
        val payload = orderDto.let {
            Payload(
                it.orderId,
                it.userId,
                it.productId,
                it.qty,
                it.unitPrice,
                it.totalPrice
            )
        }
        val kafkaOrderDto = KafkaOrderDto(schema, payload)
        var jsonInString = ""
        try {
            jsonInString = ObjectMapper().registerModule(kotlinModule()).writeValueAsString(kafkaOrderDto)
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }
        kafkaTemplate.send(topic, jsonInString)
        log.info("Order Producer sent data from the order microservice : $kafkaOrderDto" )
        return orderDto
    }
}