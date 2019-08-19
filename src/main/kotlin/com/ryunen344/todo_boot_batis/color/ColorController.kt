package com.ryunen344.todo_boot_batis.color

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid

@RestController
@Validated
class ColorController {

    @RequestMapping(value = ["/"], method = [RequestMethod.GET])
    fun isDefinedColor(@ColorConstraint @Valid @RequestParam(value = "color", required = false) color : String) : Boolean? {

        //validation済みなので値を変換して使用する
        Color.fromValue(color)

        return true
    }

}
