package com.example.secondservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/second-service")
class SecondServiceController {
    @GetMapping("/welcome")
    fun welcome(): String{
        return "Welcome to the second service."
    }

}