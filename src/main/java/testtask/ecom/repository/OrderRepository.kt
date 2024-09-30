package testtask.ecom.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import testtask.ecom.entity.OrderEntity
import testtask.ecom.entity.Status

@Repository
interface OrderRepository : JpaRepository<OrderEntity, Long> {

    fun findOrderEntityByIdAndUserId(id: Long, userId: Long?): OrderEntity?

    @Query("""
        select orders.* from orders
        where status = 'DONE'
    """, nativeQuery = true)
    fun findByStatus(status: Status) : List<OrderEntity>

    fun findOrderEntityById(id : Long): OrderEntity
}