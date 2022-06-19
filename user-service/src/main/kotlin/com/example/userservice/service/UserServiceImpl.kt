package com.example.userservice.service

import com.example.userservice.dto.RegisterUserRequest
import com.example.userservice.dto.UserResponse
import com.example.userservice.repository.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
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

    @Transactional
    override fun findAllUser(): List<UserResponse> {
        return userRepository.findAll().map(::UserResponse)
    }

    override fun findByUserId(userId: String): UserResponse {
        val userEntity = userRepository.findByUserId(userId) ?: throw UsernameNotFoundException("사용자를 찾을 수 없습니다.")
        return UserResponse(userEntity)
    }
}
