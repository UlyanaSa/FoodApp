package com.osvin.foodapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.osvin.foodapp.data.network.RetrofitInstance
import com.osvin.foodapp.pojo.Food
import com.osvin.foodapp.pojo.FoodCategory
import com.osvin.foodapp.pojo.FoodList
import com.osvin.foodapp.pojo.FoodListCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodByCategoryViewModel(): ViewModel(){
    companion object{
        private val TAG = "FoodByCategoryViewModel"
    }

    private val _foodItemByCategoryLiveData by lazy { MutableLiveData<List<FoodCategory>>() }
    var foodItemByCategoryLiveData: LiveData<List<FoodCategory>> = _foodItemByCategoryLiveData

    //создать живые данные для того, чтобы отслеживать лайки и добавления в корзину

    fun getFoodItemDetail(categoryName: String){
        RetrofitInstance.api.getFoodByCategory(categoryName).enqueue(object: Callback<FoodListCategory> {
            override fun onResponse(call: Call<FoodListCategory>, response: Response<FoodListCategory>) {
                if(response.body() != null){
                    _foodItemByCategoryLiveData.value = response.body()!!.meals

                }else
                    return
            }

            override fun onFailure(call: Call<FoodListCategory>, t: Throwable) {
                Log.e(TAG, "onResponse: ${t.message.toString()}", )
            }

        })


    }


}