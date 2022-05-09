package com.osvin.foodapp.data.firestore


import android.util.Log
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase
import com.osvin.foodapp.data.models.User
import com.osvin.foodapp.presentation.fragment.auth.LoginFragment
import com.osvin.foodapp.utils.Constants


class Firestore {
    private val mFireStore = FirebaseFirestore.getInstance()
    private val TAG = "Firestore"
    fun getCurrentUserId():String{
        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserId = ""
        if (currentUser != null)
            currentUserId = currentUser.uid
        return currentUserId
    }

    fun registerUser(userData: User):Boolean{
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

    fun getUserDetailis (fragment: Fragment){
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                Log.i(TAG, "getUserDetailis: ${document.toString()}")

                val user = document.toObject(User::class.java) // преобразовали в объект класса юзер
                when(fragment){
                    is LoginFragment -> {
                        if (user != null) {
                            fragment.userLoggedInSuccess(user = user)
                        }
                    }
                }
            }
    }

  //  fun savePhoto(photo: )
}