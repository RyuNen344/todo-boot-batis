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
import org.springframework.web.context.ServletContextAware
import java.util.function.Consumer
import javax.servlet.Filter
import javax.servlet.ServletContext



@SpringBootApplication
class TodoBootBatisApplication : CommandLineRunner, ServletContextAware {

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

    @Autowired // DIコンテナに登録されているFilterクラスの確認
    fun dumpFilters(filters : List<Filter>) {
        logger.info("--- filters in DI container ----")
        filters.forEach(Consumer<Filter> { f -> logger.info(f.toString()) })
    }

    override// サーブレットコンテナに登録されているFilterクラスの確認
    fun setServletContext(servletContext : ServletContext) {
        logger.info("--- filters in servlet context ----")
        servletContext.filterRegistrations.values.forEach { r ->
            logger.info(String.format("name:%s url-mappings:%s servlet-mappings:%s",
                    r.name, r.urlPatternMappings, r.servletNameMappings))
        }
    }
}

fun main(args : Array<String>) {
    runApplication<TodoBootBatisApplication>(*args)
}