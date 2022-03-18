package com.osvin.foodapp.domain.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

interface AuthorizationRepository {
    var app: Application
    var firebaseUserLiveData: MutableLiveData<FirebaseUser>
    var auth: FirebaseAuth



}