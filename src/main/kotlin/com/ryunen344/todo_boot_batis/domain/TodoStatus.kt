package com.ryunen344.todo_boot_batis.domain

enum class TodoStatus constructor(val string : String) {
    READY("ready"),
    DOING("doing"),
    DONE("done");


    companion object {

        fun fromString(string : String) : TodoStatus {
            return values().firstOrNull { it.string == string } ?: READY
        }

    }

    val isFinished : Boolean
        get() = this == DONE

    val isDoing : Boolean
        get() = this == DOING


}