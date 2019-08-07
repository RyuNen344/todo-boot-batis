package com.ryunen344.todo_boot_batis.config

import com.ryunen344.todo_boot_batis.util.LoggerDelegate
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.util.WebUtils
import java.util.*
import java.util.stream.Collectors
import javax.servlet.DispatcherType
import javax.servlet.ServletRequestEvent
import javax.servlet.ServletRequestListener
import javax.servlet.http.HttpServletRequest

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CustomServletRequestListener : ServletRequestListener {

    val logger by LoggerDelegate()


    override fun requestInitialized(sre : ServletRequestEvent?) {
        logger.info("request initialized")
        val httpServletRequest = sre?.servletRequest as HttpServletRequest
        logRequest(httpServletRequest)
        super.requestInitialized(sre)
    }

    override fun requestDestroyed(sre : ServletRequestEvent?) {
        logger.info("request destroyed")
        val httpServletRequest = sre?.servletRequest as HttpServletRequest
        logRequest(httpServletRequest)
        super.requestDestroyed(sre)
    }

    private fun logRequest(request : HttpServletRequest) {
        val params : String = request.parameterMap.entries.stream().map { entry -> entry.key + ":" + Arrays.toString(entry.value) }.collect(Collectors.joining(", "))

        val queryString = request.queryString
        val queryClause = if (StringUtils.hasLength(queryString)) "?$queryString" else ""
        val dispatchType = if (request.dispatcherType != DispatcherType.REQUEST) {
            "\"" + request.dispatcherType.name + "\" dispatch for "
        } else {
            ""
        }

        val message = dispatchType + request.method + " \"" + getRequestUri(request) +
                queryClause + "\", parameters={" + params + "}"

        val values = Collections.list(request.headerNames)
        val headers = values.stream().map { name -> name + ":" + Collections.list(request.getHeaders(name)) }.collect(Collectors.joining(", "))
        logger.info(message)
    }

    private fun getRequestUri(request : HttpServletRequest) : String? {
        var uri : String? = request.getAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE) as String?
        if (uri == null) {
            uri = request.requestURI
        }
        return uri
    }
}