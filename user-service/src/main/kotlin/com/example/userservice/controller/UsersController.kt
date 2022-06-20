package com.example.userservice.controller

import com.example.userservice.dto.RegisterUserRequest
import com.example.userservice.dto.UserResponse
import com.example.userservice.service.UserService
import org.springframework.core.env.Environment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
class UsersController(
    val env: Environment,
    val userService: UserService
) {

    @GetMapping("/health_check")
    fun status(request: HttpServletRequest): String {
        return "It`s Working in User Service on Port ${request.serverPort}"
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
    @GetMapping("/users")
    fun findAllUser(): ResponseEntity<List<UserResponse>> {
        val users = userService.findAllUser()
        return ResponseEntity.ok(users)
    }
    @GetMapping("/users/{userId}")
    fun findByUserId(
        @PathVariable userId: String
    ): ResponseEntity<UserResponse> {
        val users = userService.findByUserId(userId)
        return ResponseEntity.ok(users)
    }
}
