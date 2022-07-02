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

class AuthViewModel(private val app: Application): AndroidViewModel(app) {

    private val repository = AppRepository(app)

    private var _firebaseUserLiveData: MutableLiveData<FirebaseUser> = repository._firebaseUserLiveData
    private var firebaseUserLiveData: LiveData<FirebaseUser> = _firebaseUserLiveData

    private var _userLiveData: MutableLiveData<User> = repository._userLiveData
    private var userLiveData: LiveData<User> = _userLiveData

    private var _resultRegister: MutableLiveData<Boolean> = repository._resultRegister
    private var resultRegister: LiveData<Boolean> = _resultRegister

    private var _resultLogin: MutableLiveData<Boolean> = repository._resultLogin
    private var resultLogin: LiveData<Boolean> = _resultLogin

    private var _signOutLiveData: MutableLiveData<Boolean> = repository._signOutLiveData
    private var signOutLiveData: LiveData<Boolean> = _signOutLiveData

    private var _resultSaveDataUser: MutableLiveData<Boolean> = repository._resultSaveDataUser
    private var resultSaveDataUser: LiveData<Boolean> = _resultSaveDataUser

//    fun getUserId(): String? {
//        viewModelScope.launch {
//            _id.value = repository.getCurrentUserId().toString()
//        }
//
//    }

    fun saveDataUser(user:User){
        viewModelScope.launch {
            repository.saveDataUser(user)
        }
    }

    fun getUserDetail(){
        viewModelScope.launch{
            repository.getUserDetailis()
        }
    }

    fun register(user: User){
        viewModelScope.launch {
            repository.register(user)
        }
    }
    fun verifiedEmail(){
        viewModelScope.launch{
            repository.verifiedEmail()
        }
    }

    fun login(user: User) {
        viewModelScope.launch {
           repository.login(user)
        }
    }

    fun singOut(){
        viewModelScope.launch {
            repository.singOut()
        }
    }

}