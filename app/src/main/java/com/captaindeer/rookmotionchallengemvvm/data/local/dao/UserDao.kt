package com.captaindeer.rookmotionchallengemvvm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.captaindeer.rookmotionchallengemvvm.data.local.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    @Query(value = "select * from users")
    fun updatePosts(): List<UserEntity>

    @Query(value = "select * from users where :user LIKE first_name or :user LIKE last_name")
    fun getUser(user: String): List<UserEntity>

}