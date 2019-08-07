package com.ryunen344.todo_boot_batis.config

import com.ryunen344.todo_boot_batis.util.LoggerDelegate
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CustomFilter : Filter {

    val logger by LoggerDelegate()

    @Throws(ServletException::class)
    override fun init(filterConfig : FilterConfig?) {
        logger.info("init : {}", filterConfig)
        // 初期化処理を行う。このメソッドはアプリケーション起動時に呼び出される。
        // サーブレットフィルタの初期化パラメータは引数のFilterConfigから取得できる。
        // (実装は省略)
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request : ServletRequest, response : ServletResponse, filterChain : FilterChain) {
        logger.info("doFilter : {} {}", request, response)
        // ここに前処理を実装する
        // (実装は省略)
        // 後続処理(次のFilter又はServlet)を呼び出したくない場合は、このタイミングでメソッドを終了(return)すればよい。

        // 後続処理(次のFilter又はServlet)を呼び出す
        filterChain.doFilter(request, response)

        // ここに後処理を実装する
        // (実装は省略)
    }

    override fun destroy() {
        logger.info("destroy")
        // アプリケーション終了時に行う処理を実装する
        // (実装は省略)
    }
}