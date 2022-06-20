package com.example.gateway.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder

// @Configuration
class FilterConfig {
//    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route {
                    r ->
                r.path("/first-service/**")
                    .filters { f ->
                        f.addRequestHeader("first-request", "first-request-header")
                            .addResponseHeader("first-response", "first-response-header")
                    }
                    .uri("http://localhost:8001")
            }
            .route {
                    r ->
                r.path("/second-service/**")
                    .filters { f ->
                        f.addRequestHeader("second-request", "second-request-header")
                        f.addResponseHeader("second-response", "second-response-header")
                    }
                    .uri("http://localhost:8002")
            }
            .build()
    }
}
