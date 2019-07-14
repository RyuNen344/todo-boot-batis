package com.ryunen344.todo_boot_batis.domain


data class Todo(
        var id : Long?,
        var title : String,
        var detail : String,
        var status : TodoStatus
)