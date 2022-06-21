package com.example.catalogsservice.messagequeue
import com.example.catalogsservice.repository.CatalogRepository
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class KafkaConsumer(
    val repository: CatalogRepository,
) {
    companion object {
        private val log = LoggerFactory.getLogger(KafkaConsumer::class.java)
    }
    @KafkaListener(topics = ["example-catalog-topic"])
    fun updateQty(kafkaMessage: String) {
        log.info("Kafka Message -> $kafkaMessage")
        val objectMapper = ObjectMapper().registerModule(kotlinModule())
        var  map: Map<Any?, Any?> = HashMap()
        try {
            map = objectMapper.readValue(kafkaMessage, object : TypeReference<Map<Any?, Any?>>(){})
        }catch (  e: JsonProcessingException){
            e.printStackTrace()
        }
        val productId: String = map["productId"] as? String ?:throw RuntimeException("productId 찾을 수 없습니다.")
        val qty: Int = map["qty"] as? Int ?: throw RuntimeException("qty를 찾을 수 없습니다.")

        log.info(productId)
        val catalogEntity = repository.findByProductId(productId) ?: throw RuntimeException("카테고리 상품을 찾을 수 없습니다.")
        catalogEntity.stock = catalogEntity.stock - qty

    }



}