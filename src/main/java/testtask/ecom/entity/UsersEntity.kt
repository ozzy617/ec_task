package testtask.ecom.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter.All

@Entity
@Table(name = "users")
data class UserEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
        @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
        var id : Long? = null,

        val username : String,
        val password : String,

//        @Column(name = "role")
//        @ElementCollection(fetch = FetchType.EAGER)
//        @CollectionTable(name = "users_roles")
//        @Enumerated(value = EnumType.STRING)
//        val roles: Set<Roles>,

        @Enumerated(EnumType.STRING)
        @Column(name = "role", nullable = false)
        val role : Roles,

        @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
        val orders : List<Order> = mutableListOf()
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
}
