package com.example.secondservice

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/second-service")
class SecondServiceController {
    val log: Logger = LoggerFactory.getLogger(SecondServiceController::class.java)
    @Autowired
    lateinit var env : Environment
    @GetMapping("/welcome")
    fun welcome(): String{
        return "Welcome to the second service."
    }
    @GetMapping("/message")
    fun message(@RequestHeader("second-request") header: String): String{
        log.info(header)
        return "Hello World in second Service."
    }
    @GetMapping("/check")
    fun check(request: HttpServletRequest): String{
        log.info("Server port = {}", request.serverPort)
        return "Hi, there. this is a message from Second Service on PORT ${env.getProperty("local.server.port")}"
    }

}