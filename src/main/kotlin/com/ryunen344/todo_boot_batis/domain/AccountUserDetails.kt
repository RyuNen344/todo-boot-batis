package com.ryunen344.todo_boot_batis.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AccountUserDetails(private val account : Account, private val authorities : MutableCollection<out GrantedAuthority>) : UserDetails {

    override fun getAuthorities() : MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun isEnabled() : Boolean {
        return account.isEnabled
    }

    fun isAdmin() : Boolean {
        return account.isAdmin
    }

    override fun getUsername() : String {
        return account.userName
    }

    override fun isCredentialsNonExpired() : Boolean {
        return true
    }

    override fun getPassword() : String {
        return account.password
    }

    override fun isAccountNonExpired() : Boolean {
        return true
    }

    override fun isAccountNonLocked() : Boolean {
        return true
    }


}