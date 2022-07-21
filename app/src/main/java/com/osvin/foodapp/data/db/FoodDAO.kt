package com.osvin.foodapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.osvin.foodapp.pojo.Food
import com.osvin.foodapp.pojo.FoodCategory

@Dao
interface FoodDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun foodInsert(food: FoodCategory)

    @Delete
    suspend fun delete(food: FoodCategory)

    @Query("SELECT * FROM foodInformation2")
    fun getAllFoods():LiveData<List<FoodCategory>>

    @Query("SELECT * FROM foodInformation2 WHERE likes = 'true'")
    fun getAllFoodLikes():LiveData<List<FoodCategory>>

    @Query("SELECT * FROM foodInformation2 WHERE basket ='true'")
    fun getAllFoodBasket():LiveData<List<FoodCategory>>

    @Update
    fun updateFoodInfo(food:FoodCategory)




}