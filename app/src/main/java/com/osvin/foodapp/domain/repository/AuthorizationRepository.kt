package com.osvin.foodapp.domain.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser

class AuthorizationRepository {
    private lateinit var application: Application
    private lateinit var firebaseUserLiveData: MutableLiveData<FirebaseUser>


}