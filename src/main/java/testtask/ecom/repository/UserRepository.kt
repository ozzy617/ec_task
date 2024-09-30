package testtask.ecom.repository

import org.springframework.data.jpa.repository.JpaRepository
import testtask.ecom.entity.UsersEntity

interface UserRepository : JpaRepository<UsersEntity, Long> {

    fun findByUsername(username: String?): UsersEntity?

    fun findUserEntityById(id : Long) : UsersEntity?

    fun existsByUsername(username: String?): Boolean
}