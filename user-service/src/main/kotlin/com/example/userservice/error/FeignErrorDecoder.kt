package com.example.userservice.error

import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class FeignErrorDecoder(
    val env: Environment
) : ErrorDecoder {
    override fun decode(methodKey: String?, response: Response?): Exception {
        when(response!!.status()){
            404 -> {
                if (methodKey!!.contains("getOrders")){
                    return ResponseStatusException(HttpStatus.valueOf(response.status()), env.getProperty("order_service.exception.order_is_empty"))
                }
            }
        }
        return Exception(response.reason())
    }
}