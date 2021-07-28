package com.captaindeer.rookmotionchallengemvvm.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var logged = MutableLiveData<Boolean>()
    var progressVisibility = MutableLiveData<Boolean>()
    private var auth: FirebaseAuth

    init {
        auth = FirebaseAuth.getInstance()
    }

    fun logOn() {
        viewModelScope.launch {
            if (auth?.currentUser != null)
                logged.postValue(true)
            else
                logged.postValue(false)
        }
    }

    fun logIn(email: String, password: String) {
        viewModelScope.launch {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        logged.postValue(true)
                    } else {
                        logged.postValue(false)
                    }
                    progressVisibility.postValue(false)
                }
            progressVisibility.postValue(true)
        }
    }
}