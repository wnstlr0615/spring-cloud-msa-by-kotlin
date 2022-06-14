package com.example.ecoomerce

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class EcommerceApplication
fun main(args: Array<String>) {
	runApplication<EcommerceApplication>(*args)
}
