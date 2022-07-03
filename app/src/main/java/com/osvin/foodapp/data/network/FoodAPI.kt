package com.osvin.foodapp.data.network

import com.osvin.foodapp.pojo.FoodList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodAPI {
    companion object{
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }


    @GET("filter.php?")
    fun getRecommendationFoodItems(@Query("c") categoryName:String): Call<FoodList>
}