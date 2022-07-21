package com.osvin.foodapp.presentation.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.osvin.foodapp.data.network.RetrofitInstance
import com.osvin.foodapp.data.repository.AppRepository
import com.osvin.foodapp.pojo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(app: Application, private var repository: AppRepository): AndroidViewModel(app){
    companion object{
        private val TAG = "HomeViewModel"
    }

    private val _foodListLiveData by lazy {MutableLiveData<List<FoodCategory>>()}
    var foodListLiveData: LiveData<List<FoodCategory>> = _foodListLiveData

    private val _categoryListLiveData by lazy {MutableLiveData<List<Category>>()}
    var categoryListLiveData: LiveData<List<Category>> = _categoryListLiveData

    fun getFoodItems() {
        RetrofitInstance.api.getRecommendationFoodItems(categoryName = "Seafood").enqueue(object :
            Callback<FoodListCategory> {
            override fun onResponse(call: Call<FoodListCategory>, response: Response<FoodListCategory>) {
                val foodList = response.body()?.meals
                _foodListLiveData.value = foodList as ArrayList<FoodCategory>?
            }

            override fun onFailure(call: Call<FoodListCategory>, t: Throwable) {
                Log.e(TAG, "onFailure getFoodItems: ${t.message}",)
            }

        })
    }

    fun getCategoryItems(){
        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                val categoryList = response.body()?.categories
                _categoryListLiveData.value = categoryList as ArrayList<Category>
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.e(TAG, "onFailure getCategories: ${t.message}", )
            }

        })
    }


}