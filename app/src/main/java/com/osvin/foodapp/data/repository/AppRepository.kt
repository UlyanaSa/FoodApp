package com.osvin.foodapp.data.repository

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.osvin.foodapp.R
import com.osvin.foodapp.data.models.User
import com.osvin.foodapp.presentation.fragment.auth.RegisterFragment
import com.osvin.foodapp.utils.Constants

class AppRepository(private var app: Application) {
    private val TAG = "AppRepository"

    lateinit var _firebaseUserLiveData: MutableLiveData<FirebaseUser>
 //   var firebaseUserLiveData: LiveData<FirebaseUser> = _firebaseUserLiveData

    lateinit var _userLiveData: MutableLiveData<User>
  //  var userLiveData: LiveData<User> = _userLiveData

    lateinit var _resultRegister: MutableLiveData<Boolean>
  //  var resultRegister: LiveData<Boolean> = _resultRegister

    lateinit var _resultLogin: MutableLiveData<Boolean>
  //  var resultLogin: LiveData<Boolean> = _resultLogin

    lateinit var _signOutLiveData: MutableLiveData<Boolean>
  //  var signOutLiveData: LiveData<Boolean> = _signOutLiveData

    lateinit var _resultSaveDataUser: MutableLiveData<Boolean>


    private var auth = FirebaseAuth.getInstance()
    private val mFireStore = FirebaseFirestore.getInstance()



//    fun getCurrentUserId(){
//        if(auth.currentUser != null){
//            _firebaseUserLiveData.value = auth.currentUser
//            _userLiveData.value!!.id = _firebaseUserLiveData.value!!.uid
//        }else{
//            Toast.makeText(app, R.string.incorrect_data, Toast.LENGTH_SHORT).show()
//        }
//    }

    fun saveDataUser(user: User){
        val pathDocument = user.id
        mFireStore.collection(Constants.USERS)
            .document(pathDocument)// создает коллекцию users с набором парамтеров юзера. ключ колеекции - юзер айди
            .set(user, SetOptions.merge()) //merge объединяет данные, если такой документ уже существует.Или созд новый док
            .addOnSuccessListener {
                _resultSaveDataUser.value = true
            }.addOnFailureListener {
                _resultSaveDataUser.value = false
            }

    }

    fun getUserDetailis (){
            mFireStore.collection(Constants.USERS)
                .document(_firebaseUserLiveData.value!!.uid)
                .get()
                .addOnSuccessListener { document ->
                    Log.i(TAG, "getUserDetailis: ${document.toString()}")

                    _userLiveData.value = document.toObject(User::class.java)!! // преобразовали в объект класса юзер
                }
    }

    fun register(user: User) {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(app, R.string.successful_registration, Toast.LENGTH_SHORT).show()
                    _firebaseUserLiveData.value = auth.currentUser
                    _firebaseUserLiveData.value!!.sendEmailVerification().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            user.id = _firebaseUserLiveData.value!!.uid
                            _userLiveData.value = user
                            Toast.makeText(app, R.string.email_sent, Toast.LENGTH_SHORT).show()
                        } else {
                           Log.e(TAG, "AppRepository: ${task.exception}")
                        }
                    }

                } else {
                    Log.e(TAG, "AppRepository: ${task.exception}")
                }
            }
    }

    fun verifiedEmail(){
        if (auth.currentUser != null) {
            _resultRegister.value = if (auth.currentUser!!.isEmailVerified) {
                _firebaseUserLiveData.value = auth.currentUser
                true
            } else {
                Toast.makeText(app, R.string.verify_email, Toast.LENGTH_SHORT).show()
                false
            }
        }
    }

    fun login(user: User){
        auth.signInWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                   _firebaseUserLiveData.value = auth.currentUser
                    if(_resultRegister.value == true){
                        _resultLogin.value = true
                    }
                }else{
                    _resultLogin.value = false
                    Log.e(TAG, "AppRepository: ${it.exception}")
                }
            }
    }

    fun singOut() {
        auth.signOut()
        _signOutLiveData.value = true
    }
}