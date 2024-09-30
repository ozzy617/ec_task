package testtask.ecom.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import testtask.ecom.service.UserService

@Configuration
@EnableWebSecurity
class ApplicationConfiguration(
        private val jwtAuthenticationFilter: JwtFilter,
        private val userService: UserService
) {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
                .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
                .components(
                        Components()
                                .addSecuritySchemes("bearerAuth",
                                        SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .info(Info()
                        .title("Task list API")
                        .description("Spring boot application")
                        .version("1.0")
                )
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { obj -> obj.disable() }
                .cors { cors ->
                    cors.configurationSource {
                        val corsConfiguration = CorsConfiguration()
                        corsConfiguration.allowedOriginPatterns = listOf("*")
                        corsConfiguration.allowedMethods = listOf(
                                "GET",
                                "POST",
                                "PUT",
                                "DELETE",
                                "OPTIONS"
                        )
                        corsConfiguration.allowedHeaders = listOf("*")
                        corsConfiguration.allowCredentials = true
                        corsConfiguration
                    }
                }
                .authorizeHttpRequests { request ->
                    request
                            .requestMatchers("/auth/**").permitAll()
                            .requestMatchers("/swagger-ui/**", "/swagger-resources/*", "/v3/api-docs/**").permitAll()
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .anyRequest().authenticated()
                }
                .sessionManagement { manager: SessionManagementConfigurer<HttpSecurity?> ->
                    manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                }
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userService.userDetailsService())
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
        return config.authenticationManager
    }
}