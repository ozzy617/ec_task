package testtask.ecom.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import testtask.ecom.dto.JwtResponse
import testtask.ecom.dto.AuthRequest
import testtask.ecom.service.AuthService
import testtask.ecom.service.JwtService
import testtask.ecom.service.UserService

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth Controller", description = "Auth API")
class AuthController(
        private val authService: AuthService,
) {


    @PostMapping("/register")
    @Operation(summary = "register user")
    fun register(@RequestBody request: AuthRequest): JwtResponse {
       return authService.register(request)
    }

    @PostMapping("/login")
    @Operation(summary = "login user")
    fun login(@RequestBody request: AuthRequest): JwtResponse {
        return authService.login(request)
    }
}