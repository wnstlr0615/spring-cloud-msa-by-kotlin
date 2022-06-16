package com.example.userservice.service

import com.example.userservice.dto.RegisterUserRequest
import com.example.userservice.dto.UserResponse

interface UserService {
    fun addUser(request: RegisterUserRequest): UserResponse
}
