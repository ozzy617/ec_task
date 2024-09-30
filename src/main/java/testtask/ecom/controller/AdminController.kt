package testtask.ecom.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import testtask.ecom.dto.ProductDto
import testtask.ecom.service.AdminService
import testtask.ecom.service.ProductService

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin Controller", description = "Admin API")
class AdminController(
        private val adminService: AdminService
) {

    @PatchMapping("/product/change")
    @Operation(summary = "change product values")
    fun changeProductValues(@RequestBody productDto: ProductDto):ResponseEntity<Unit> {
        adminService.changeProductValues(productDto)
        return ResponseEntity.ok().build()
    }

    @PatchMapping("/order/{id}")
    @Operation(summary = "complete order")
    fun changeOrderStatus(@PathVariable id : Long): ResponseEntity<Unit> {
        adminService.changeOrderStatus(id)
        return ResponseEntity.ok().build()
    }

}