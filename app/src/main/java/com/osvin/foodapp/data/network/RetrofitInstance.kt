package com.osvin.foodapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  RetrofitInstance {

    val api: FoodAPI by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(FoodAPI.BASE_URL)
            .build()
            .create(FoodAPI::class.java)
    }
}


