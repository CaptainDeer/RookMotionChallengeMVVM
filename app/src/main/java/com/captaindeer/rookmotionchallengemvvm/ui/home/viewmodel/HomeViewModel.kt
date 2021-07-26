package com.captaindeer.rookmotionchallengemvvm.ui.home.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.captaindeer.rookmotionchallengemvvm.data.local.LocalDatabase
import com.captaindeer.rookmotionchallengemvvm.data.local.entities.UserEntity
import com.captaindeer.rookmotionchallengemvvm.data.remote.RetrofitBuilder
import com.captaindeer.rookmotionchallengemvvm.ui.home.view.HomeFragmentDirections
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class HomeViewModel(app: Application) : AndroidViewModel(app) {

    lateinit var allUsers: MutableLiveData<List<UserEntity>>
    val progressVisibility = MutableLiveData<Boolean>()
    val message = MutableLiveData<String>()
    private var retrofit = RetrofitBuilder()
    private val database = LocalDatabase(app)

    init {
        allUsers = MutableLiveData()
    }

    fun downloadData() {
        progressVisibility.value = true
        viewModelScope.launch {
            retrofit = RetrofitBuilder()
            val response = retrofit.getAllUsers()
            if (response.isSuccessful) {
                message.value = "Downloading"
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
                progressVisibility.value = false
            } else {
                Log.e("TAG", "No se obtuvieron datos de la API")
                message.postValue("Not Connected")
                progressVisibility.value = false
            }
        }
    }

    fun getAllUsersObserver(): MutableLiveData<List<UserEntity>> {
        return allUsers
    }

    suspend fun insertUserInfo(userEntity: UserEntity) {
        coroutineScope {
            launch {
                database.userDao().insert(userEntity)
                getAllUsers()
            }
        }
    }

    suspend fun deleteUserInfo(userEntity: UserEntity) {
        coroutineScope {
            launch {
                database.userDao().delete(userEntity)
                getAllUsers()
            }
        }
    }

    suspend fun getAllUsers() {
        coroutineScope {
            launch {
                val userList = database.userDao().refreshData()
                allUsers.postValue(userList)
            }
        }
    }
}