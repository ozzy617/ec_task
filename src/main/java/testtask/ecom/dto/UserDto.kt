package testtask.ecom.dto

import io.swagger.v3.oas.annotations.media.Schema
import testtask.ecom.entity.UsersEntity

@Schema(description = "User Dto")
data class UserDto(
        val id: Long?,

        @Schema(description = "username", example = "user")
        val username: String? = null,

        @Schema(description = "name", example = "Ivan")
        val name : String? = null,

        @Schema(description = "surname", example = "Ivanov")
        val surname: String? = null,

        @Schema(description = "location", example = "Moscow")
        val location: String? = null,
)
    fun UsersEntity.toDto(): UserDto {
        return UserDto(
                id = this.id,
                username = this.username,
                name = this.name,
                surname = this.surname,
                location = this.location
        )
    }
