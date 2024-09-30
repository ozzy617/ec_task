package testtask.ecom.entity

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(name = "orders")
class Order (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id : Long? = null,

        val title : String?,

        val status : Status,

        val created : OffsetDateTime,

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        val user : UsersEntity,

        @CollectionTable(name = "orders_goods")
        @OneToMany
        @JoinColumn(name = "good_id")
        val goods: List<Good> = mutableListOf()
) {


}