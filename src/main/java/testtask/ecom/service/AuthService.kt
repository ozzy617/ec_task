package testtask.ecom.service

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import testtask.ecom.dto.JwtResponse
import testtask.ecom.dto.AuthRequest
import testtask.ecom.entity.Roles
import testtask.ecom.entity.UsersEntity

@Service
class AuthService(
        private val userService: UserService,
        private val jwtService: JwtService,
        private val authenticationManager: AuthenticationManager,
        private val passwordEncoder: PasswordEncoder
) {

    fun register(request: AuthRequest): JwtResponse {
        val user = UsersEntity(
                username = request.username,
                password = passwordEncoder.encode(request.password),
                role = Roles.ROLE_USER
        )
        userService.createUser(user)
        return JwtResponse(jwtService.generateToken(user))
    }

    fun login(request: AuthRequest) : JwtResponse {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
                request.username,
                request.password))
        val user = userService
                .userDetailsService()
                .loadUserByUsername(request.username)

        return JwtResponse(jwtService.generateToken(user))
    }
}