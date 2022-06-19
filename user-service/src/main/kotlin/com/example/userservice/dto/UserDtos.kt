package com.example.userservice.dto

import com.example.userservice.entity.UserEntity
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class RegisterUserRequest(
    @field:NotNull(message = "Email cannot be null")
    @field:Size(min = 2, message = "Email not be less than two characters")
    @field:Email
    private val email: String,

    @field:NotNull(message = "Password cannot be null")
    @field:Size(min = 2, max = 16, message = "Password must be equal or grater than 8 characters and less than 16 characters")
    private val pwd: String,

    @field:NotNull(message = "Name cannot be null")
    @field:Size(min = 2, message = "Name not be less than two characters")
    private val name: String
) {
    fun toEntity(): UserEntity {
        return UserEntity(email, name, pwd, UUID.randomUUID().toString())
    }
}

data class UserResponse(
    val email: String,
    val name: String,
    val userId: String,
    val orders: List<OrderResponse> = mutableListOf()
) {
    constructor(user: UserEntity) : this(
        user.email,
        user.name,
        user.userId
    )
}
