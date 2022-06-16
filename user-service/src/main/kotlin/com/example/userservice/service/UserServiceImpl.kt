package com.example.userservice.service

import com.example.userservice.dto.RegisterUserRequest
import com.example.userservice.dto.UserResponse
import com.example.userservice.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserServiceImpl(
    val userRepository: UserRepository,
    val encoder: PasswordEncoder
) : UserService {
    override fun addUser(request: RegisterUserRequest): UserResponse {
        val userEntity = request.toEntity().apply { this.password = encoder.encode(password) }
        val saveEntity = userRepository.save(userEntity)
        return UserResponse(saveEntity)
    }
}
