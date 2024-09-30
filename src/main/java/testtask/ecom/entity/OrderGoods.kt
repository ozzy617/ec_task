package testtask.ecom.entity

import jakarta.persistence.*

@Entity
@Table(name = "orders_goods")
data class OrderGoods(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_goods_seq")
        @SequenceGenerator(name = "orders_goods_seq", sequenceName = "orders_goods_seq", allocationSize = 1)
        val id : Long? = null,

        @ManyToOne
        @JoinColumn(name = "order_id")
        val order: OrderEntity,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "good_id")
        val good: GoodEntity,

){
}