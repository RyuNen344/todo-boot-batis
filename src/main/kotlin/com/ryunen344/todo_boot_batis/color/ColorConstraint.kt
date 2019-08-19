package com.ryunen344.todo_boot_batis.color


import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.VALUE_PARAMETER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [ColorValidator::class])
annotation class ColorConstraint(val message : String = "not defined color", val groups : Array<KClass<*>> = [], val payload : Array<KClass<out Payload>> = [])
