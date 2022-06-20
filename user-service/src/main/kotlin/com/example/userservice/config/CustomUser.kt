package com.example.userservice.config

import com.example.userservice.entity.UserEntity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User

class CustomUser(
    val userEntity: UserEntity
) : User(userEntity.email, userEntity.password, listOf(SimpleGrantedAuthority("ROLE_USER")))
