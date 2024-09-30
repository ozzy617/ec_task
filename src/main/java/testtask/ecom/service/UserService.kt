package testtask.ecom.service

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import testtask.ecom.dto.UserDto
import testtask.ecom.entity.UsersEntity
import testtask.ecom.exception.ResourceNotFoundException
import testtask.ecom.repository.UserRepository

@Service
class UserService(
        private val userRepository: UserRepository
) {

    @Transactional
    fun deleteUser(username : String) {
        val user = userRepository.findByUsername(username) ?: throw ResourceNotFoundException("Пользователь не найден")
        userRepository.delete(user)
    }

    @Transactional
    fun updateUser(username : String, userDto: UserDto) {
        val user = userRepository.findByUsername(username) ?: throw ResourceNotFoundException("Пользователь не найден")
        val updatedUser = user.copy(
                name = userDto.name ?: user.name,
                surname = userDto.surname ?: user.surname,
                location = userDto.location ?: user.location,
        )
        userRepository.save(updatedUser)
    }

    @Transactional
    fun createUser(user: UsersEntity): UsersEntity? {
        if (userRepository.existsByUsername(user.username)) {
            throw IllegalStateException("User already exists")
        }
        return userRepository.save(user)
    }


    fun getByUsername(username : String) : UsersEntity {
        return userRepository.findByUsername(username) ?: throw ResourceNotFoundException("User not found")
    }

    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { username -> getByUsername(username) }
    }

    fun getById(id : Long) : UsersEntity? {
        return userRepository.findUserEntityById(id) ?: throw ResourceNotFoundException("User not found")
    }
}