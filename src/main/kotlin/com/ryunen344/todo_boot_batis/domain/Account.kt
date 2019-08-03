package com.ryunen344.todo_boot_batis.domain

data class Account(
        var id : Long?,
        var userName : String,
        var password : String,
        var isEnabled : Boolean,
        var isAdmin : Boolean
)