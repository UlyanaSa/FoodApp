package com.osvin.foodapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose


@Entity(tableName = "foodInformation2")
data class FoodCategory(
    @PrimaryKey()
    var idMeal: String,
    var strMeal: String,
    var strMealThumb: String,
    @Expose
    var likes: Boolean = false,
    @Expose
    var basket: Boolean = false,
    @Expose
    var price: String="0$",
    @Expose
    var count: Int= 0,
)