package com.ryunen344.todo_boot_batis.color

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ColorValidator : ConstraintValidator<ColorConstraint, String> {


    override fun initialize(constraintAnnotation : ColorConstraint?) {}

    override fun isValid(value : String?, context : ConstraintValidatorContext) : Boolean {
        //未設定は許可
        if (value == null) return true

        //空文字は不許可,値からenum引っ張れれば許可
        return if (value.isEmpty()) false else Color.fromValue(value) != null

    }
}
