package com.osvin.foodapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.osvin.foodapp.pojo.Food
import com.osvin.foodapp.pojo.FoodCategory

@Database(entities = [FoodCategory::class], version = 3)
@TypeConverters(FoodTypeConvertor::class)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDAO

    companion object{

        var INSTANCE: FoodDatabase? = null
        fun getInstance(context: Context):FoodDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    FoodDatabase::class.java,
                    "food.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as FoodDatabase
        }
    }
}