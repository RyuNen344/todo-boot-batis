package com.ryunen344.todo_boot_batis

import com.ryunen344.todo_boot_batis.domain.Account
import com.ryunen344.todo_boot_batis.domain.Todo
import com.ryunen344.todo_boot_batis.domain.TodoStatus
import com.ryunen344.todo_boot_batis.repository.AccountRepository
import com.ryunen344.todo_boot_batis.repository.TodoRepository
import com.ryunen344.todo_boot_batis.util.LoggerDelegate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.Transactional

@SpringBootApplication
class TodoBootBatisApplication : CommandLineRunner {

    @Autowired(required = false)
    private lateinit var todoRepository : TodoRepository

    @Autowired
    private lateinit var accountRepository : AccountRepository

    val logger by LoggerDelegate()

    @Transactional
    override fun run(vararg args : String?) {
        var insertTodo : Todo = Todo(null, "init title", "init detail", TodoStatus.READY)

        logger.info(insertTodo.toString())

        todoRepository.insert(insertTodo)
        logger.info(insertTodo.toString())


        var insertAccount = Account(1, "hogehoge", "password", true, true)
        logger.info(insertAccount.toString())

        accountRepository.insert(insertAccount)
        logger.info(insertAccount.toString())


    }
}

fun main(args : Array<String>) {
    runApplication<TodoBootBatisApplication>(*args)
}