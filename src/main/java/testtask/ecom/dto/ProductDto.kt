package testtask.ecom.dto

import io.swagger.v3.oas.annotations.media.Schema
import testtask.ecom.entity.GoodEntity
import testtask.ecom.entity.ProductEntity

@Schema(description = "Product Dto")
data class ProductDto(

        @Schema(description = "title", example = "bread")
        val title : String?,

        @Schema(description = "quantity", example = "10")
        val quantity: Long,

        @Schema(description = "price", example = "45.3")
        val price: Double
)

fun ProductEntity.toDto () : ProductDto {
    return ProductDto(
            title = this.title,
            quantity = this.quantity!!,
            price = this.price!!
    )
}