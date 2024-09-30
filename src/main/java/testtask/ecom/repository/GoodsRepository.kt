package testtask.ecom.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import testtask.ecom.entity.GoodEntity

@Repository
interface GoodsRepository : JpaRepository<GoodEntity, Long> {

    fun findByTitle(username: String): GoodEntity
}