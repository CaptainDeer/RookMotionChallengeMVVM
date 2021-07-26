package com.captaindeer.rookmotionchallengemvvm.ui.userdetail.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.captaindeer.rookmotionchallengemvvm.data.local.LocalDatabase
import com.captaindeer.rookmotionchallengemvvm.data.local.entities.UserEntity

class UserDetailViewModel(app: Application) : AndroidViewModel(app) {

    private val database = LocalDatabase(app)

    fun getUser(id: Int): UserEntity {
        return database.userDao().getUserById(id)
    }
}