package com.osvin.foodapp.presentation.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.osvin.foodapp.data.network.RetrofitInstance
import com.osvin.foodapp.data.repository.AppRepository
import com.osvin.foodapp.pojo.Food
import com.osvin.foodapp.pojo.FoodList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(app: Application, private var repository: AppRepository): AndroidViewModel(app){
    companion object{
        private val TAG = "HomeViewModel"
    }

    private val _foodListLiveData by lazy {MutableLiveData<ArrayList<Food>>()}
    var foodListLiveData: LiveData<ArrayList<Food>> = _foodListLiveData


    fun getFoodItems(){
        RetrofitInstance.api.getRecommendationFoodItems(categoryName = "Seafood").enqueue(object :
            Callback<FoodList> {
            override fun onResponse(call: Call<FoodList>, response: Response<FoodList>) {
                val foodList = response.body()?.meals
                _foodListLiveData.value = foodList as ArrayList<Food>?
            }

            override fun onFailure(call: Call<FoodList>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}", )
            }

        })

    }

}