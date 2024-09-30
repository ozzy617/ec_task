package testtask.ecom.entity

import jakarta.persistence.*

@Entity
@Table(name = "goods")
class GoodEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id : Long? = null,

        val title : String,

        var quantity : Long,

        val price : Double,


        ) {

}