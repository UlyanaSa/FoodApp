package com.osvin.foodapp.data.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.osvin.foodapp.R
import com.osvin.foodapp.data.db.FoodDatabase
import com.osvin.foodapp.data.models.User
import com.osvin.foodapp.utils.Constants

class AppRepository(private var app: Application) {
    private val TAG = "AppRepository"
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var mFireStore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var firebaseUser: FirebaseUser //= auth.currentUser
   // private var foodDatabase:FoodDatabase = FoodDatabase.getInstance(app)



    fun currentUser():Boolean{
        var result = false
        if(auth.currentUser != null){
            firebaseUser = auth.currentUser!!
            result = true
            Toast.makeText(app, "Hello, ${firebaseUser!!.email.toString()}", Toast.LENGTH_SHORT)
        }
        else{
            Toast.makeText(app, "Authorisation please", Toast.LENGTH_SHORT)
            result = false
        }
        return result
    }

    fun saveDataUser(user: MutableLiveData<User>): Boolean {
        var resultSaveDataUser = false
        val pathDocument = user.value?.id
        if (pathDocument != null) {
            mFireStore.collection(Constants.USERS)
                .document(pathDocument)// создает коллекцию users с набором парамтеров юзера. ключ колеекции - юзер айди
                .set(user, SetOptions.merge()) //merge объединяет данные, если такой документ уже существует.Или созд новый док
                .addOnSuccessListener {
                    resultSaveDataUser = true
                }.addOnFailureListener {
                    resultSaveDataUser = false
                }
        }
        return resultSaveDataUser
    }

    fun getUserDetailis ():MutableLiveData<User>{
        val user = MutableLiveData<User>()
            mFireStore.collection(Constants.USERS)
                .document(firebaseUser!!.uid)
                .get()
                .addOnSuccessListener { document ->
                    Log.i(TAG, "getUserDetailis: ${document.toString()}")

                    user.value = document.toObject(User::class.java)!! // преобразовали в объект класса юзер
                }
        return user
    }

    fun register(user: MutableLiveData<User>):Boolean{
        //var registerUser = User()
        var result = false
        auth.createUserWithEmailAndPassword(user.value!!.email, user.value!!.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(app, R.string.successful_registration, Toast.LENGTH_SHORT).show()
                    firebaseUser = auth.currentUser!!
                   firebaseUser!!.sendEmailVerification().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            user.value!!.id = firebaseUser!!.uid
                           // registerUser = user
                            saveDataUser(user)
                            result = true
                            Log.d(TAG, "register: ${saveDataUser(user)}")
                            Toast.makeText(app, R.string.email_sent, Toast.LENGTH_SHORT).show()
                        } else {
                           Log.e(TAG, "AppRepository: ${task.exception}")
                        }
                    }

                } else {
                    Log.e(TAG, "AppRepository: ${task.exception}")
                }
            }
        return result
    }

    fun verifiedEmail():Boolean{
        var resultVerify = false

        resultVerify= if (auth.currentUser!!.isEmailVerified) {
                firebaseUser = auth.currentUser!!
                true
            } else {
                Toast.makeText(app, R.string.verify_email, Toast.LENGTH_SHORT).show()
                false
            }
        return resultVerify
    }

    fun login(user: MutableLiveData<User>): Boolean{
        var resultLogin = false
        auth.signInWithEmailAndPassword(user.value!!.email, user.value!!.password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                   firebaseUser = auth.currentUser!!
                    if(verifiedEmail()){
                        resultLogin = true
                        Toast.makeText(app, "${R.string.successful_login} ${user.value!!.name}", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    resultLogin = false
                    Log.e(TAG, "AppRepository: ${it.exception}")
                }
            }
        return resultLogin
    }

    fun singOut() {
        auth.signOut()
    }




}

