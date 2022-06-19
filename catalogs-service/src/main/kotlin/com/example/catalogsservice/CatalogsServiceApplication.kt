package com.example.catalogsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class CatalogsServiceApplication

fun main(args: Array<String>) {
    runApplication<CatalogsServiceApplication>(*args)
}
