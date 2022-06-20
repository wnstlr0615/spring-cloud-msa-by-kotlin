package com.example.userservice.repository

import com.example.userservice.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByUserId(userId: String): UserEntity?
    fun findByEmail(username: String?): UserEntity?
}
