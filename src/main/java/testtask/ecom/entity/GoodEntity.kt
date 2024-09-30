package testtask.ecom.entity

import jakarta.persistence.*

@Entity
@Table(name = "goods", schema = "db_schema")
class GoodEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_id_seq")
        @SequenceGenerator(name = "goods_id_seq", sequenceName = "goods_id_seq", allocationSize = 1)
        var id : Long? = null,

        val title : String,

        var quantity : Long,

        val price : Double,


        ) {

}