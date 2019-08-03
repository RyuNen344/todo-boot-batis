package com.ryunen344.todo_boot_batis.config

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter
import javax.servlet.http.HttpServletRequest

class CustomPreAuthenticatedProcessingFilter : AbstractPreAuthenticatedProcessingFilter() {

    // APIキーを取得
    override fun getPreAuthenticatedCredentials(request : HttpServletRequest?) : Any {
        return request?.getHeader("X-Auth-Token") ?: ""
    }

    // principalはスルー
    override fun getPreAuthenticatedPrincipal(request : HttpServletRequest?) : Any {
        return ""
    }
}