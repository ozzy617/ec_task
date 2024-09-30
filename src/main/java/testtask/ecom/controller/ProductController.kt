package testtask.ecom.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import testtask.ecom.dto.ProductDto
import testtask.ecom.service.ProductService

@RestController
@RequestMapping("/api/product")
@Tag(name = "Product Controller", description = "Product API")
class ProductController(
        private val productService: ProductService
) {

    @GetMapping
    @Operation(summary = "get catalogue")
    fun getCatalogue(): List<ProductDto> {
        return productService.getAllProducts()
    }

    @PostMapping("/create")
    @Operation(summary = "createProduct order")
    fun createProduct(@RequestBody productDto: ProductDto): ResponseEntity<Unit> {
        productService.createProduct(productDto)
        return ResponseEntity.ok().build()
    }
}