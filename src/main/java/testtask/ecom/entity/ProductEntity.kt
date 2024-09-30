package testtask.ecom.entity

import jakarta.persistence.*

@Entity
@Table(name = "product")
data class ProductEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
        @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
        var id : Long? = null,

        val title : String,

        var quantity : Long,

        val price : Double,

        ) {

}