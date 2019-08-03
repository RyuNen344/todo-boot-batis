package com.ryunen344.todo_boot_batis.service

import com.ryunen344.todo_boot_batis.domain.Account
import com.ryunen344.todo_boot_batis.domain.AccountUserDetails
import com.ryunen344.todo_boot_batis.repository.AccountRepository
import com.ryunen344.todo_boot_batis.util.LoggerDelegate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Service


@Service
class AccountUserDetailsServiceImpl : AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Autowired
    private lateinit var accountRepository : AccountRepository

    val logger by LoggerDelegate()

    private fun loadUserByUsername(username : String?) : UserDetails {
        logger.info("loadUserByUsername")

        if (username.isNullOrEmpty()) throw UsernameNotFoundException("Not Found")

        val account = accountRepository.selectByUserName(username)

        account?.let {
            throw UsernameNotFoundException("Not Found")
        }

        return AccountUserDetails(account!!, getAuthorities(account))

    }

    override fun loadUserDetails(token : PreAuthenticatedAuthenticationToken?) : UserDetails {
        logger.info("loadUserDetails")

        // MyPreAuthenticatedProcessingFilterで取得したAPIキー
        val credential = token?.credentials

        if (credential.toString() == "") {
            throw UsernameNotFoundException("User not found")
        }

        logger.info(credential.toString())

        val account = accountRepository.selectByUserName(credential.toString())
                ?: throw UsernameNotFoundException("Not Found")

        //val account = Account(1,"hogehoge","password", true,true)

        return AccountUserDetails(account, getAuthorities(account))

    }

    private fun getAuthorities(account : Account) : MutableCollection<out GrantedAuthority> {

        return if (account.isAdmin) {
            AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER")
        } else {
            AuthorityUtils.createAuthorityList("ROLE_USER")
        }

    }

}