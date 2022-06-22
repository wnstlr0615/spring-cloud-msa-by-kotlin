package com.example.orderservice.messagequeue


import com.example.orderservice.dto.OrderRequest
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(
    val kafkaTemplate: KafkaTemplate<String , String>
) {
    companion object {
        private val log = LoggerFactory.getLogger(KafkaProducer::class.java)
    }
    fun send(topic: String, orderRequest: OrderRequest): OrderRequest {
        val mapper = ObjectMapper().registerModule(kotlinModule())
        var jsonInString = ""
        try {
            jsonInString = mapper.writeValueAsString(orderRequest)
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }
        kafkaTemplate.send(topic, jsonInString)
        log.info("Kafka Producer sent data from the Order microservice: $orderRequest")
        return  orderRequest
    }
}