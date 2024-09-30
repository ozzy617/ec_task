package testtask.ecom.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import testtask.ecom.dto.OrderDto
import testtask.ecom.dto.toDto
import testtask.ecom.entity.GoodEntity
import testtask.ecom.entity.OrderEntity
import testtask.ecom.entity.OrderGoods
import testtask.ecom.entity.Status
import testtask.ecom.exception.ResourceNotFoundException
import testtask.ecom.repository.*

@Service
class OrderService(
        private val userRepository: UserRepository,
        private val orderRepository: OrderRepository,
        private val goodsRepository: GoodsRepository,
        private val orderGoodRepository: OrderGoodRepository,
        private val productRepository : ProductRepository
) {

   @Transactional
    fun createOrder(username : String, orderDto: OrderDto) {
        val user = userRepository.findByUsername(username) ?: throw ResourceNotFoundException("User not found")
        val orderEntity = OrderEntity(
                status = Status.IN_PROGRESS,
                user = user,
        )
       val goods = orderDto.goods
       goods.forEach {it ->
           var product  = productRepository.findByTitle(it.title!!)
           val quantity = product.quantity
           val quanReq = it.quantity

           var goodQuantity = it.quantity
           if (quantity - quanReq < 0) {
               goodQuantity = product.quantity
               product.quantity = 0
           } else {
               product.quantity = quantity - quanReq
           }
           val good = GoodEntity(
                   price = product.price,
                   quantity = goodQuantity,
                   title = product.title
           )
           val orderGood = OrderGoods(
                   order = orderEntity,
                   good = good
           )
           productRepository.save(product)
           orderGoodRepository.save(orderGood)
           goodsRepository.save(good)
       }
       orderRepository.save(orderEntity)
    }

    fun findAllOrders(username: String): List<OrderDto> {
        val user = userRepository.findByUsername(username)
        val orderDto = user!!.orders.map { it.toDto() }
        return orderDto
    }

    fun findOrderById(username: String, id : Long): OrderDto {
        val user = userRepository.findByUsername(username) ?: throw ResourceNotFoundException("User not found")
        val order = orderRepository.findOrderEntityByIdAndUserId(id, user.id) ?: throw ResourceNotFoundException("Order not found")
        return order.toDto()
    }
}