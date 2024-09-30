package testtask.ecom.dto

import io.swagger.v3.oas.annotations.media.Schema
import testtask.ecom.entity.OrderEntity
import testtask.ecom.entity.Status
import testtask.ecom.exception.ResourceNotFoundException
import java.time.OffsetDateTime

@Schema(description = "Order Dto")
data class OrderDto(
        val id: Long? = null,
        val status: Status? = null,
        val createdDate: OffsetDateTime? = null,

        @Schema(description = "goods", example = """""")
        val goods: List<GoodsDto> = mutableListOf(),
)
fun OrderEntity.toDto () : OrderDto {
    return OrderDto(
            id = this.id,
            createdDate = this.created,
            status = this.status,
            goods = this.goodsOrders.map { it.good.toDto() ?: throw ResourceNotFoundException("Goods are not found") }.toList()
            )
}