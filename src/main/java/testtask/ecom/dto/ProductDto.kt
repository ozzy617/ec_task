package testtask.ecom.dto

import testtask.ecom.entity.GoodEntity

data class GoodsDto(
        val title : String?,
        val quantity: Long,
)

fun GoodEntity.toDto () : GoodsDto {
    return GoodsDto(
            title = this.title,
            quantity = this.quantity
    )
}