package testtask.ecom.repository

import org.springframework.data.jpa.repository.JpaRepository
import testtask.ecom.entity.UserEntity

interface UserRepository : JpaRepository<UserEntity, Long> {

}