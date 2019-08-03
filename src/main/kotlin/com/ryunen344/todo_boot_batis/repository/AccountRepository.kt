package com.ryunen344.todo_boot_batis.repository

import com.ryunen344.todo_boot_batis.domain.Account
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface AccountRepository {

    @Insert("INSERT INTO account (userName, password, isEnabled, isAdmin) VALUES (#{userName}, #{password}, #{isEnabled}, #{isAdmin})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    fun insert(account : Account) : Boolean

    @Select("select * from account")
    fun select() : List<Account>

    @Select("select * from account where id = #{id}")
    fun selectById(id : Long) : Account

    @Select("select * from account where userName = #{userName}")
    fun selectByUserName(userName : String) : Account?

    @Update("select * from account")
    fun update(account : Account) : Account

    @Delete("delete from account")
    fun delete() : Boolean

    @Delete("delete from account where id = #{id}")
    fun deleteById(id : Long) : Boolean

}