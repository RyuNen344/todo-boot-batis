package com.ryunen344.`todo-boot-batis`

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
class TodoBootBatisApplication

fun main(args : Array<String>) {
    runApplication<TodoBootBatisApplication>(*args)
}

