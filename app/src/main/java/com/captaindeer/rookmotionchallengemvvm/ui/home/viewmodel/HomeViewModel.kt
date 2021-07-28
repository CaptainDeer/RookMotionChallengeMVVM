package com.captaindeer.rookmotionchallengemvvm.ui.home.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.captaindeer.rookmotionchallengemvvm.data.local.LocalDatabase
import com.captaindeer.rookmotionchallengemvvm.data.local.entities.UserEntity
import com.captaindeer.rookmotionchallengemvvm.data.remote.RetrofitBuilder
import kotlinx.coroutines.launch

class HomeViewModel(app: Application) : AndroidViewModel(app) {

    val allUsers = MutableLiveData<List<UserEntity>>()
    val progressVisibility = MutableLiveData<Boolean>()
    val message = MutableLiveData<String>()
    private var retrofit = RetrofitBuilder()
    private val database = LocalDatabase(app)


    fun downloadData() {
        viewModelScope.launch {
            retrofit = RetrofitBuilder()
            val response = retrofit.getAllUsers()
            if (response.isSuccessful) {
                response.body()?.data?.forEach { user ->
                    insertUserInfo(
                        UserEntity(
                            user.id,
                            user.first_name,
                            user.last_name,
                            user.email,
                            user.avatar
                        )
                    )
                }
            } else {
                Log.e("TAG", "No se obtuvieron datos de la API")
                progressVisibility.value = false
            }
            getAllUsers()
            progressVisibility.value = false
        }
        progressVisibility.value = true
    }

    private fun insertUserInfo(userEntity: UserEntity) {
        viewModelScope.launch {
            database.userDao().insert(userEntity)
        }
    }

    fun deleteUserInfo(userEntity: UserEntity) {
        viewModelScope.launch {
            database.userDao().delete(userEntity)
            getAllUsers()
        }
    }

    fun getAllUsers() {
        viewModelScope.launch {
            allUsers.postValue(database.userDao().refreshData())
        }
    }

    fun getUser(data: String) {
        viewModelScope.launch {
            allUsers.postValue(database.userDao().getUser(data))
        }
    }
}