package testtask.ecom.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import testtask.ecom.entity.GoodEntity
import testtask.ecom.entity.ProductEntity

@Repository
interface ProductRepository : JpaRepository<ProductEntity, Long> {

    fun findByTitle(username: String): ProductEntity
}