package testtask.ecom.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import testtask.ecom.dto.UserDto
import testtask.ecom.dto.toDto
import testtask.ecom.service.UserService

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Controller", description = "User API")
class UserController(
        private val userService: UserService
) {

    @PatchMapping("/update")
    @Operation(summary = "Update user")
    fun updateUser(@AuthenticationPrincipal userDetails: UserDetails, @RequestBody userDto: UserDto): ResponseEntity<Unit> {
        userService.updateUser(userDetails.username, userDto)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    @Operation(summary = "get user")
    fun getUserByUsername(@AuthenticationPrincipal userDetails: UserDetails) : UserDto {
        return userService.getByUsername(userDetails.username).toDto()
    }

    @DeleteMapping("/delete")
    @Operation(summary = "delete user")
    fun deleteUserByUsername(@AuthenticationPrincipal userDetails: UserDetails) : ResponseEntity<Unit> {
        userService.deleteUser(userDetails.username)
        return ResponseEntity.ok().build()
    }

    fun getOrders() {}
}