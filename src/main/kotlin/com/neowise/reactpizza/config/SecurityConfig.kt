package com.neowise.reactpizza.config

import com.neowise.reactpizza.security.jwt.JwtConfigurer
import com.neowise.reactpizza.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.WebSecurityConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
class SecurityConfig @Autowired constructor(
        private val jwtTokenProvider: JwtTokenProvider
    ) : WebSecurityConfigurerAdapter() {

    companion object {
        const val ADMIN_ENDPOINT = "/api/v1/admin/**"
        const val LOGIN_ENDPOINT = "/api/v1/auth/login"
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    protected override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(LOGIN_ENDPOINT).permitAll()
            .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .apply(JwtConfigurer(jwtTokenProvider))

    }
}