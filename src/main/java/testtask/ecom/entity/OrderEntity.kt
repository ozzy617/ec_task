package testtask.ecom.entity

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(name = "orders")
data class OrderEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
        @SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
        var id : Long? = null,

        @Enumerated(EnumType.STRING)
        @Column(name = "status", nullable = false)
        val status : Status,

        val created : OffsetDateTime = OffsetDateTime.now(),

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        val user : UsersEntity,

        @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
        val goodsOrders: List<OrderGoods> = mutableListOf()
) {
        override fun toString(): String {
                return "Order(id=$id)"
        }


}