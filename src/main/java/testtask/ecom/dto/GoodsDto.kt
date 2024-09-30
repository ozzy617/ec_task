package testtask.ecom.dto

import io.swagger.v3.oas.annotations.media.Schema
import testtask.ecom.entity.GoodEntity

@Schema(description = "Good Dto")
data class GoodsDto(

        @Schema(description = "title", example = "bread")
        val title : String?,

        @Schema(description = "quantity", example = "3")
        val quantity: Long,
)

fun GoodEntity.toDto () : GoodsDto {
    return GoodsDto(
            title = this.title,
            quantity = this.quantity
    )
}