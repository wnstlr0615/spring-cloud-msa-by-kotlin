package com.example.gateway.filter

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
@Component
class AuthorizationHeaderFilter(
    val env: Environment
) : AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>(Config::class.java) {

    class Config

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
            val request = exchange.request
            if (!request.headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                return@GatewayFilter onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED)
            }
            val authorizationHeader = request.headers[HttpHeaders.AUTHORIZATION]!![0]
            val jwt = authorizationHeader.substring("Bearer ".length)
            if (!isJwtValid(jwt)) {
                return@GatewayFilter onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED)
            }
            return@GatewayFilter chain.filter(exchange)
        }
    }

    private fun isJwtValid(jwt: String): Boolean {
        try {
            JWT.require(Algorithm.HMAC512(env.getProperty("token.secret"))).build().verify(jwt)
        } catch (e: Exception) {
            log.info("Jwt Token Exception", e)
            return false
        }
        return true
    }

    private fun onError(exchange: ServerWebExchange?, errorMessage: String, httpStatus: HttpStatus): Mono<Void> {
        exchange!!.response.statusCode = httpStatus
        log.error(errorMessage)
        return exchange.response.setComplete()
    }
    companion object {
        val log: Logger = LoggerFactory.getLogger(AuthorizationHeaderFilter::class.java)
    }
}
