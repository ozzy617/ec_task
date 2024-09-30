package testtask.ecom.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import testtask.ecom.dto.GoodsDto
import testtask.ecom.dto.ProductDto
import testtask.ecom.dto.toDto
import testtask.ecom.entity.ProductEntity
import testtask.ecom.exception.ResourceNotFoundException
import testtask.ecom.repository.ProductRepository

@Service
class ProductService(
        private val productRepository: ProductRepository) {

    fun getAllProducts() : List<ProductDto> {
        return productRepository.findAll().map { it.toDto() }
    }

    @Transactional
    fun createProduct(productDto : ProductDto) {
        val updatedEntity = ProductEntity(
                title = productDto.title!!,
                price = productDto.price,
                quantity = productDto.quantity,
        )
        productRepository.save(updatedEntity)
    }

}