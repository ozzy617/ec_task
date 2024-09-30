package testtask.ecom.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
data class UsersEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
        @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
        var id : Long? = null,

        @Column(unique = true, nullable = false, name = "username")
        private val username : String,

        private val password : String,

        val name : String? = null,

        val surname : String? = null,

        val location : String? = null,

//        @Column(name = "role")
//        @ElementCollection(fetch = FetchType.EAGER)
//        @CollectionTable(name = "users_roles")
//        @Enumerated(value = EnumType.STRING)
//        val roles: Set<Roles>,

        @Enumerated(EnumType.STRING)
        @Column(name = "role", nullable = false)
        val role : Roles,

        @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
        val orders : List<OrderEntity> = mutableListOf()

) : UserDetails {
        override fun getAuthorities(): List<GrantedAuthority> {
                return listOf(SimpleGrantedAuthority(role.name))
        }

        override fun getPassword() = this.password

        override fun getUsername() = this.username

        override fun isAccountNonExpired(): Boolean {
                return true        }

        override fun isAccountNonLocked(): Boolean {
                return true        }

        override fun isCredentialsNonExpired(): Boolean {
                return true        }

        override fun isEnabled(): Boolean {
                return true        }

        override fun toString(): String {
                return "UserInfoEntity(id=$id, username='$username', password='$password')"
        }
}

