package testtask.ecom.entity

import jakarta.persistence.*

@Entity
@Table(name = "goods")
class Good(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id : Long? = null,

        val title : String,

        val quantity : Long,

        val price : Double,


) {

}