package com.osvin.foodapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.osvin.foodapp.data.models.User
import com.osvin.foodapp.data.repository.AppRepository
import kotlinx.coroutines.launch

class AuthViewModel(app: Application, private var repository: AppRepository): AndroidViewModel(app) {


    private val _userLiveData by lazy {MutableLiveData<User>(User())}
    var userLiveData: LiveData<User> = _userLiveData

    private val _resultRegister by lazy {MutableLiveData<Boolean>()}
    var resultRegister: LiveData<Boolean> = _resultRegister

    private val _resultLogin by lazy {MutableLiveData<Boolean>()}
    var resultLogin: LiveData<Boolean> = _resultLogin

    private val _signOutLiveData by lazy {MutableLiveData<Boolean>()}
    var signOutLiveData: LiveData<Boolean> = _signOutLiveData

    private val _resultVerify by lazy {MutableLiveData<Boolean>()}
    var resultVerify: LiveData<Boolean> = _resultVerify


//    fun getUserId(): String? {
//        viewModelScope.launch {
//            _id.value = repository.getCurrentUserId().toString()
//        }
//
//    }

//    fun saveDataUser(user:User){
//        _resultSaveDataUser.value = repository.saveDataUser(user)
//    }

//    fun getUserDetail(){
//        repository.getUserDetailis()
//    }

    fun register(){
        _resultRegister.value = repository.register(_userLiveData)
    }
    fun verifiedEmail(){
        _resultVerify.value = repository.verifiedEmail()

    }

    fun login() {
        _resultLogin.value = repository.login(_userLiveData)
    }

    fun singOut(){
        repository.singOut()
    }

}



