package testtask.ecom.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Auth request")
data class AuthRequest (

        @Schema(description = "username", example = "user")
        val username: String,

        @Schema(description = "password", example = "1234")
        val password: String,
)