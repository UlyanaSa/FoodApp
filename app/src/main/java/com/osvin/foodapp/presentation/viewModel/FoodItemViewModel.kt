package com.osvin.foodapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.osvin.foodapp.data.db.FoodDatabase
import com.osvin.foodapp.data.network.RetrofitInstance
import com.osvin.foodapp.pojo.Food
import com.osvin.foodapp.pojo.FoodCategory
import com.osvin.foodapp.pojo.FoodList
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodItemViewModel(val foodDatabase: FoodDatabase): ViewModel(){
    companion object{
        private val TAG = "FoodItemViewModel"
    }
    private val _foodDBLiveData by lazy {MutableLiveData<FoodCategory>()}
    var foodDBLiveData: LiveData<FoodCategory> = _foodDBLiveData

    private val _foodItemLiveData by lazy {MutableLiveData<Food>()}
    var foodItemLiveData: LiveData<Food> = _foodItemLiveData

    private val _foodCount by lazy {MutableLiveData<Int>()}
    var foodCount: LiveData<Int> = _foodCount

    fun plus(coutFood: Int){
        var cout = coutFood
        cout += 1
        _foodCount.value = cout
    }

    fun minus(coutFood: Int){
        var cout = coutFood
        if(cout>0){
            cout--
        }else{
            cout = 0
        }
        _foodCount.value = cout
    }

    fun insertFood(food: FoodCategory){
        viewModelScope.launch{
            foodDatabase.foodDao().foodInsert(food)
        }
    }

    fun deleteFood(food: FoodCategory){
        viewModelScope.launch{
            foodDatabase.foodDao().delete(food)
        }
    }

    fun updateFood(food: FoodCategory){
        viewModelScope.launch{
            foodDatabase.foodDao().updateFoodInfo(food)
        }
    }

    fun getAllLikesFood(){
        viewModelScope.launch {
            foodDatabase.foodDao().getAllFoodLikes()
        }
    }

    fun getAllBasketFood(){
        viewModelScope.launch {
            foodDatabase.foodDao().getAllFoodBasket()
        }
    }

    fun getFoodItemDetail(id: String){
        RetrofitInstance.api.getFoodDetails(id).enqueue(object: Callback<FoodList> {
            override fun onResponse(call: Call<FoodList>, response: Response<FoodList>) {
                if(response.body() != null){
                    _foodItemLiveData.value = response.body()!!.meals[0]
                }else
                    return
            }

            override fun onFailure(call: Call<FoodList>, t: Throwable) {
                Log.e(TAG, "onResponse: ${t.message.toString()}", )
            }
        })
    }


}