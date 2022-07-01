package com.osvin.foodapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.osvin.foodapp.data.models.User
import com.osvin.foodapp.data.repository.AuthorizationRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val app: Application): AndroidViewModel(app) {

   // private lateinit var repository: AuthorizationRepository
   // private lateinit var _firebaseUserLiveData: MutableLiveData<FirebaseUser>
  //  var firebaseUserLiveData: LiveData<FirebaseUser> = _firebaseUserLiveData
   // private lateinit var _userLoggedMutableLiveData: MutableLiveData<Boolean>
  //  var userLoggedMutableLiveData: LiveData<Boolean> = _userLoggedMutableLiveData

    private val repository = AuthorizationRepository(app)
    private lateinit var _resultRegister : MutableLiveData<Boolean>
    var resultRegister: LiveData<Boolean> = _resultRegister
    private lateinit var _resultLogin: MutableLiveData<Boolean>
    var resultLogin: LiveData<Boolean> = _resultLogin
    private lateinit var _id: MutableLiveData<String>;
    var id: LiveData<String> = _id
    private lateinit var _resultVerify: MutableLiveData<Boolean>;
    var resultVerify: LiveData<Boolean> = _resultVerify
    private lateinit var _signOut: MutableLiveData<Boolean>;
    var signOut: LiveData<Boolean> = _signOut

    fun getUserId(): String? {
        viewModelScope.launch {
            _id.value = repository.getCurrentUserId().toString()
        }
        return _id.value
    }

    fun saveDataUser(user:User):Boolean{
        var result = false
        viewModelScope.launch {
            result = repository.saveDataUser(user)
        }
        return result
    }

    fun getUserDetail():User{
        var user = User()
        viewModelScope.launch{
            user = repository.getUserDetailis()
        }
        return user
    }

    fun register(user: User){
        _resultRegister.value = false
        viewModelScope.launch {
            _resultRegister.value = repository.register(user)
        }
    }
    fun verifiedEmail(){
        viewModelScope.launch{
            _resultVerify.value = repository.verifiedEmail()
        }
    }

    fun login(user: User) {
        viewModelScope.launch {
           _resultLogin.value = repository.login(user)
        }
    }

    fun singOut(){
        viewModelScope.launch {
            repository.singOut()
        }
    }

}