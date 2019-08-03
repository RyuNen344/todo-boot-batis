package com.ryunen344.todo_boot_batis.config

import com.ryunen344.todo_boot_batis.service.AccountUserDetailsServiceImpl
import com.ryunen344.todo_boot_batis.util.LoggerDelegate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AccountStatusUserDetailsChecker
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken


@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter(true) {

    val logger by LoggerDelegate()

    override fun configure(http : HttpSecurity?) {
        logger.info("")

        // csrf無効化
        http
                ?.csrf()?.disable()

        // basic認証無効
        http
                ?.httpBasic()?.disable()

        // 全リクエストに対してFilterを適用
        http
                ?.authorizeRequests()
                ?.mvcMatchers("/**")
                ?.authenticated()
                ?.and()
                ?.addFilter(preAuthenticatedProcessingFilter())
                ?.exceptionHandling()
                ?.authenticationEntryPoint(http401UnauthorizedEntryPoint())

        // リクエスト毎に認証を実施
        http?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(web : WebSecurity?) {
        logger.info("")
        super.configure(web)
    }

    // サービス
    @Bean
    fun authenticationUserDetailsService() : AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
        logger.info("")
        return AccountUserDetailsServiceImpl()
    }


    @Bean
    fun http401UnauthorizedEntryPoint() : Http401UnauthorizedEntryPoint {
        logger.info("")
        return Http401UnauthorizedEntryPoint()
    }

    // フィルター登録
    @Bean
    fun preAuthenticatedAuthenticationProvider() : PreAuthenticatedAuthenticationProvider {
        logger.info("")
        return PreAuthenticatedAuthenticationProvider().apply {
            setPreAuthenticatedUserDetailsService(authenticationUserDetailsService())
            setUserDetailsChecker(AccountStatusUserDetailsChecker())
        }
    }

    @Bean
    fun preAuthenticatedProcessingFilter() : CustomPreAuthenticatedProcessingFilter {
        logger.info("")
        return CustomPreAuthenticatedProcessingFilter().apply {
            setAuthenticationManager(authenticationManager())
        }
    }

}