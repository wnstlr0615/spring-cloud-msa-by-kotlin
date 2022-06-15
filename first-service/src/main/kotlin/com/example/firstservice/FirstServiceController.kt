package com.example.firstservice

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/first-service")
class FirstServiceController {
    val log = LoggerFactory.getLogger(FirstServiceController::class.java)
    @GetMapping("/welcome")
    fun welcome(): String{
        return "Welcome to the First service."
    }
    @GetMapping("/message")
    fun message(@RequestHeader("first-request") header: String): String{
        log.info(header)
        return "Hello World in First Service."
    }

    @GetMapping("/check")
    fun check(): String{
        return "Hi, there. this is a message from First Service"
    }
}