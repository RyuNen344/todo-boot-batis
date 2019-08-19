package com.ryunen344.todo_boot_batis.color

enum class Color private constructor(val color : String) {

    RED("red"),
    BLUE("blue"),
    GREEN("green");


    companion object {

        fun fromValue(color : String) : Color? {
            for (c in Color.values()) {
                if (c.color == color)
                    return c
            }
            return null
        }
    }

}
