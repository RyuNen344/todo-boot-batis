package com.ryunen344.todo_boot_batis.config

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class Http401UnauthorizedEntryPoint : AuthenticationEntryPoint {


    override fun commence(request : HttpServletRequest?, response : HttpServletResponse?, authException : AuthenticationException?) {
        response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied")
    }


}