package com.osvin.foodapp.data.repository

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.osvin.foodapp.R
import com.osvin.foodapp.data.models.User
import com.osvin.foodapp.presentation.fragment.auth.RegisterFragment
import com.osvin.foodapp.utils.Constants

class AuthorizationRepository(private var app: Application) {
    private val TAG = "AuthorizationRepository"
   // private lateinit var firebaseUserLiveData: MutableLiveData<FirebaseUser>
   // private lateinit var userLoggedMutableLiveData: MutableLiveData<Boolean>
    private var auth = FirebaseAuth.getInstance()
    private val mFireStore = FirebaseFirestore.getInstance()


    fun getCurrentUserId(): String? {
        val currentUser = auth.currentUser
        return currentUser?.uid
    }

    fun saveDataUser(userData: User):Boolean{
        val pathDocument = userData.id
        var result = true
        mFireStore.collection(Constants.USERS)
            .document(pathDocument)// создает коллекцию users с набором парамтеров юзера. ключ колеекции - юзер айди
            .set(userData, SetOptions.merge()) //merge объединяет данные, если такой документ уже существует.Или созд новый док
            .addOnSuccessListener {
                result = true
            }.addOnFailureListener {
                result = false
            }
        return result
    }

    fun getUserDetailis ():User{
        var user = User()
        getCurrentUserId()?.let {
            mFireStore.collection(Constants.USERS)
                .document(it)
                .get()
                .addOnSuccessListener { document ->
                    Log.i(TAG, "getUserDetailis: ${document.toString()}")

                    user = document.toObject(User::class.java)!! // преобразовали в объект класса юзер
    //                when(fragment){
    //                    is LoginFragment -> {
    //                        if (user != null) {
    //                            fragment.userLoggedInSuccess(user = user)
    //                        }
    //                    }
    //                }
                }
        }
        return user
    }

    fun register(user: User): Boolean {
        var result = false
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                   // firebaseUserLiveData.postValue(auth.currentUser)
                    Toast.makeText(app, R.string.successful_registration, Toast.LENGTH_SHORT).show()
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    user.id = firebaseUser.uid

                    if (saveDataUser(user)) {
                        Toast.makeText(app, R.string.data_saved, Toast.LENGTH_SHORT).show()
                    } else {
                       // Log.e(RegisterFragment.TAG, "registerUser: Error while register the user")
                    }
                    firebaseUser.sendEmailVerification().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                          //  firebaseUserLiveData.postValue(auth.currentUser)
                            Toast.makeText(app, R.string.email_sent, Toast.LENGTH_SHORT).show()
                            result = true
                        } else {
                           // Log.e(RegisterFragment.TAG, "registerUser: ${task.exception}")
                        }
                    }
                }
            }
        return result
    }

    fun verifiedEmail():Boolean{
        var result = false
        if (auth.currentUser != null) {
            result = if (auth.currentUser!!.isEmailVerified) {
               // firebaseUserLiveData.postValue(auth.currentUser)
                true
            } else {
                Toast.makeText(app, R.string.verify_email, Toast.LENGTH_SHORT).show()
                false
            }
        }
        return result
    }

    fun login(user: User):Boolean{
        var result = false
        auth.signInWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                   // firebaseUserLiveData.postValue(auth.currentUser)
                    if(verifiedEmail()){
                        result = true
                    }
                }else{
                    Log.e(TAG, "onViewCreated: ${it.exception}")
                }
            }
        return result
    }

    fun singOut() {
        auth.signOut()
        // проверяем вышел пользователь или нет
       // userLoggedMutableLiveData.postValue(true)
    }
}