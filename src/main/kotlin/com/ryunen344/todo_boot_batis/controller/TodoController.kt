package com.ryunen344.todo_boot_batis.controller

import com.ryunen344.todo_boot_batis.domain.Todo
import com.ryunen344.todo_boot_batis.domain.TodoStatus
import com.ryunen344.todo_boot_batis.service.TodoServiceImpl
import com.ryunen344.todo_boot_batis.util.LoggerDelegate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/todo")
class TodoController @Autowired constructor(private val todoServiceImpl : TodoServiceImpl) {

    val logger by LoggerDelegate()

    @RequestMapping(path = [""], method = [RequestMethod.GET])
    fun getTodo() : List<Todo>? {
        logger.info("getTodo")
        return todoServiceImpl.findAll()
    }

    @RequestMapping(path = ["/{id}"], method = [RequestMethod.GET])
    fun getTodoOne(@PathVariable("id") id : Long) : Todo? {
        logger.info("getTodoOne/$id")
        return todoServiceImpl.findById(id)
    }

    @RequestMapping(path = ["/{title}/{detail}"], method = [RequestMethod.POST])
    fun addTodo(@PathVariable("title") title : String, @PathVariable("detail") detail : String) : Boolean {
        logger.info("addTodo/$title/$detail")
        var insertTodo : Todo = Todo(null, title, detail, TodoStatus.READY)
        return todoServiceImpl.addTodo(insertTodo)
    }

    @RequestMapping(path = ["/{id}/{title}/{detail}/{status}"], method = [RequestMethod.PUT])
    fun editTodo(@PathVariable("id") id : Long, @PathVariable("title") title : String, @PathVariable("detail") detail : String, @PathVariable("status") status : TodoStatus) : Boolean {
        logger.info("editTodo/$id/$title/$detail")
        var editTodo : Todo = Todo(id, title, detail, status)
        return todoServiceImpl.addTodo(editTodo)
    }

    @RequestMapping(path = [""], method = [RequestMethod.DELETE])
    fun deleteTodo() : Boolean {
        logger.info("deleteTodo")
        return todoServiceImpl.deleteAll()
    }

    @RequestMapping(path = ["/{id}"], method = [RequestMethod.DELETE])
    fun deleteTodoOne(@PathVariable("id") id : Long) : Boolean {
        logger.info("deleteTodoOne/$id")
        return todoServiceImpl.deleteById(id)
    }


}