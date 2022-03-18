package com.neowise.reactpizza.security.jwt

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtTokenFilter
@Autowired constructor(
    private val jwtTokenProvider: JwtTokenProvider
) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        jwtTokenProvider.resolveToken(request as HttpServletRequest)?.let { token ->
            if (jwtTokenProvider.validateToken(token)) {
                jwtTokenProvider.authentication(token)?.let {
                    SecurityContextHolder
                        .getContext()
                        .authentication = it
                }
            }
        }

        chain.doFilter(request, response)
    }
}