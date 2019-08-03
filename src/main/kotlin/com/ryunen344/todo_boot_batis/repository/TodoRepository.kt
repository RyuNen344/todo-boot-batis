package com.ryunen344.todo_boot_batis.repository

import com.ryunen344.todo_boot_batis.domain.Todo
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface TodoRepository {

    @Insert("INSERT INTO todo (title, detail, status) VALUES (#{title}, #{detail}, #{status})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    fun insert(todo : Todo) : Boolean

    @Select("select * from todo")
    fun select() : List<Todo>

    @Select("select * from todo where id = #{id}")
    fun selectById(id : Long) : Todo

    @Update("select * from todo")
    fun update(todo : Todo) : Todo

    @Delete("delete from todo")
    fun delete() : Boolean

    @Delete("delete from todo where id = #{id}")
    fun deleteById(id : Long) : Boolean

}