package com.example.userservice.controller

import com.example.userservice.dto.RegisterUserRequest
import com.example.userservice.dto.UserResponse
import com.example.userservice.service.UserService
import org.springframework.core.env.Environment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/")
class UsersController(
    val env: Environment,
    val userService: UserService
) {

    @GetMapping("/health_check")
    fun status(): String {
        return "It`s Working in User Service"
    }

    @GetMapping("/welcome")
    fun welcome(): String? {
        return env.getProperty("greeting.message")
    }

    @PostMapping("/users")
    fun userAdd(@RequestBody @Valid request: RegisterUserRequest): ResponseEntity<UserResponse> {
        val userResponse = userService.addUser(request)
        return ResponseEntity.ok(userResponse)
    }
}
