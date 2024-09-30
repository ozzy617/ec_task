package testtask.ecom.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import testtask.ecom.dto.ProductDto
import testtask.ecom.entity.ProductEntity
import testtask.ecom.entity.Status
import testtask.ecom.exception.ResourceNotFoundException
import testtask.ecom.repository.OrderRepository
import testtask.ecom.repository.ProductRepository

@Service
class AdminService(
        private val productRepository: ProductRepository,
        private val orderRepository: OrderRepository
) {


    @Transactional
    fun changeProductValues(productDto : ProductDto) {
        val product = productRepository.findByTitle(productDto.title!!) ?: throw ResourceNotFoundException("Product not found")
        val updatedEntity = product.copy(
                price = productDto.price ?: product.price,
                quantity = productDto.quantity ?: product.quantity
        )
        productRepository.save(updatedEntity)
    }

    @Transactional
    fun changeOrderStatus(id : Long) {
        val order = orderRepository.findOrderEntityById(id) ?: throw ResourceNotFoundException("Order not found")
        val updatedOrder = order.copy(
                status = Status.DONE
        )
        orderRepository.save(updatedOrder)

    }
}