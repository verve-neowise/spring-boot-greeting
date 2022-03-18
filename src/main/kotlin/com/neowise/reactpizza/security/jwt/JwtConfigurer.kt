package com.neowise.reactpizza.security.jwt

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtConfigurer
    @Autowired constructor(
        private val jwtTokenProvider: JwtTokenProvider
    ) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(httpSecurity: HttpSecurity) {
        val tokenFilter = JwtTokenFilter(jwtTokenProvider)
        httpSecurity.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}