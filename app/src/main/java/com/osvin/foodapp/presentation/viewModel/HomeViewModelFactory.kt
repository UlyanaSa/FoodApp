package com.osvin.foodapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.osvin.foodapp.data.repository.AppRepository
import java.security.InvalidParameterException

class HomeViewModelFactory(private val app: Application, private var repository: AppRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(app, repository) as T
        }
        throw InvalidParameterException("invalid constructor")
    }
}