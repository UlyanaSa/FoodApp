package com.osvin.foodapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.osvin.foodapp.data.repository.AuthorizationRepository
import java.security.InvalidParameterException
import java.util.*

class AuthViewModelFactory(private val app: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AuthViewModel::class.java)){
            return AuthViewModel(app) as T
        }
        throw InvalidParameterException("invalid constructor")
    }
}