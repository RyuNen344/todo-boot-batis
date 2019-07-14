package com.ryunen344.todo_boot_batis.service

import com.ryunen344.todo_boot_batis.domain.Todo
import org.springframework.stereotype.Service

@Service
interface TodoService {

    fun addTodo(todo : Todo) : Boolean
    fun findById(id : Long) : Todo?
    fun findAll() : List<Todo>?
    fun update(todo : Todo) : Todo
    fun deleteById(id : Long) : Boolean
    fun deleteAll() : Boolean
}