package testtask.ecom.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import testtask.ecom.dto.OrderDto
import testtask.ecom.service.OrderService

@RestController
@RequestMapping("/api/order")
@Tag(name = "Order Controller", description = "Order API")
class OrderController(
        private val orderService: OrderService
) {

    @PostMapping("/create")
    @Operation(summary = "create order")
    fun createOrder(@AuthenticationPrincipal userDetails: UserDetails, @RequestBody orderDto: OrderDto) : ResponseEntity<Unit> {
        orderService.createOrder(userDetails.username, orderDto)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    @Operation(summary = "get all orders")
    fun getAllOrders(@AuthenticationPrincipal userDetails: UserDetails) : List<OrderDto> {
        return  orderService.findAllOrders(userDetails.username)
    }

    @GetMapping("/{id}")
    @Operation(summary = "get order by id order")
    fun getOrderById(@AuthenticationPrincipal userDetails: UserDetails, @PathVariable id: Long) : OrderDto {
        return orderService.findOrderById(userDetails.username, id)
    }
}