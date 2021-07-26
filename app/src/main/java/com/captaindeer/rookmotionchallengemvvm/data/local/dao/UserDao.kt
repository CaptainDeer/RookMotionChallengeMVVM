package com.captaindeer.rookmotionchallengemvvm.data.local.dao

import androidx.room.*
import com.captaindeer.rookmotionchallengemvvm.data.local.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    @Delete()
    fun delete(user: UserEntity)

    @Query(value = "select * from users")
    fun refreshData(): List<UserEntity>

    @Query(value = "select * from users where :user LIKE first_name or :user LIKE last_name")
    fun getUser(user: String): List<UserEntity>

    @Query(value = "select * from users where :user LIKE id")
    fun getUserById(user: Int): UserEntity

}