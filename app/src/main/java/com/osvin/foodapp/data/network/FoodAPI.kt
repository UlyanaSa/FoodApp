package com.osvin.foodapp.data.network

import com.osvin.foodapp.pojo.CategoryList
import com.osvin.foodapp.pojo.FoodCategory
import com.osvin.foodapp.pojo.FoodList
import com.osvin.foodapp.pojo.FoodListCategory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodAPI {
    companion object{
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }

    @GET("filter.php?")
    fun getRecommendationFoodItems(@Query("c") categoryName:String): Call<FoodListCategory>

    @GET("lookup.php?")
    fun getFoodDetails(@Query("i") id: String): Call<FoodList>

    @GET("categories.php")
    fun getCategories(): Call<CategoryList>

    @GET("filter.php")
    fun getFoodByCategory(@Query("c") categoryName:String): Call<FoodListCategory>
}
