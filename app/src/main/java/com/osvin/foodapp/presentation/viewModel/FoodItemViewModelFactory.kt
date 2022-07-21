package com.osvin.foodapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.osvin.foodapp.data.db.FoodDatabase
import com.osvin.foodapp.data.repository.AppRepository
import java.security.InvalidParameterException

class FoodItemViewModelFactory(private val foodDatabase: FoodDatabase):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FoodItemViewModel::class.java)){
            return FoodItemViewModel(foodDatabase) as T
        }
        throw InvalidParameterException("invalid constructor")
    }
}