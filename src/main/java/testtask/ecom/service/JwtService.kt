package testtask.ecom.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import testtask.ecom.entity.UsersEntity
import java.security.Key
import java.util.*

@Service
class JwtService {
    @Value("\${token.signing.key}")
    private lateinit var key: String


    fun extractUserName(token: String): String {
        return extractClaim(token, Claims::getSubject);
    }


    fun generateToken(userDetails: UserDetails): String {
        val claims = mutableMapOf<String, Any>()
        if (userDetails is UsersEntity) {
            claims["id"] = userDetails.id!!
            claims["role"] = userDetails.role
        }
        return generateToken(claims, userDetails)
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val userName = extractUserName(token);
        return (userName == userDetails.username) && !isTokenExpired(token);
    }


    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims: Claims = extractAllClaims(token)
        return claimsResolver(claims)
    }


    private fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.username)
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + 100000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }


    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }


    private fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }


    private fun extractAllClaims(token: String): Claims {
        return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token).body
    }


    private fun getSigningKey(): Key {
        val keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}