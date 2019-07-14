package com.ryunen344.todo_boot_batis.service

import com.ryunen344.todo_boot_batis.domain.Todo
import com.ryunen344.todo_boot_batis.repository.TodoRepository
import com.ryunen344.todo_boot_batis.util.LoggerDelegate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TodoServiceImpl @Autowired constructor(private val todoRepository : TodoRepository) : TodoService {

    val logger by LoggerDelegate()

    override fun addTodo(todo : Todo) : Boolean {
        logger.info("addTodo")
        return todoRepository.insert(todo)
    }

    override fun findById(id : Long) : Todo? {
        logger.info("addTodo")
        return todoRepository.selectById(id)
    }

    override fun findAll() : List<Todo>? {
        logger.info("findAll")
        return todoRepository.select()
    }

    override fun update(todo : Todo) : Todo {
        logger.info("update")
        return todoRepository.update(todo)
    }

    override fun deleteById(id : Long) : Boolean {
        logger.info("deleteById")
        return todoRepository.deleteById(id)
    }

    override fun deleteAll() : Boolean {
        logger.info("deleteAll")
        return todoRepository.delete()
    }

}