package com.example.catalogsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CatalogsServiceApplication

fun main(args: Array<String>) {
    runApplication<CatalogsServiceApplication>(*args)
}
