package com.osvin.foodapp.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.osvin.foodapp.R
import com.osvin.foodapp.databinding.ListFoodsBinding
import com.osvin.foodapp.pojo.Food
import com.osvin.foodapp.pojo.FoodCategory
import com.osvin.foodapp.pojo.FoodList
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

class RecommendationAdapter(): RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder>() {

    private var foodsList = ArrayList<FoodCategory>()
    fun setFoods(foodsList: ArrayList<FoodCategory>){
        this.foodsList = foodsList
        notifyDataSetChanged()
    }
    lateinit var onClickItem: ((FoodCategory)->Unit)
//    lateinit var onClickLikes: ((FoodCategory)->Unit)
//    lateinit var onClickBasket: ((FoodCategory)->Unit)




    class RecommendationViewHolder(val binding: ListFoodsBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        return RecommendationViewHolder(ListFoodsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.binding.tNameFood.text = foodsList[position].strMeal
        holder.binding.tPrice.text = ThreadLocalRandom.current().nextInt(100).toString()+"$"
        foodsList[position].price = holder.binding.tPrice.text as String
        Glide.with(holder.itemView)
            .load(foodsList[position].strMealThumb)
            .into(holder.binding.imageFood)
        holder.itemView.setOnClickListener {
            onClickItem.invoke(foodsList[position])
        }



//        holder.binding.bLikeFood.setOnClickListener {
//            if( foodsList[position].likes == true){
//                holder.binding.bLikeFood.setBackgroundColor(R.color.primary)
//                foodsList[position].likes = false
//            }else{
//                holder.binding.bLikeFood.setBackgroundColor(Color.RED)
//                foodsList[position].likes = true
//            }
//            onClickLikes.invoke(foodsList[position])
//        }
//
//        holder.binding.bBasket.setOnClickListener {
//            if( foodsList[position].likes == true){
//                holder.binding.bLikeFood.setBackgroundColor(R.color.primary)
//                foodsList[position].likes = false
//            }else{
//                holder.binding.bLikeFood.setBackgroundColor(Color.RED)
//                foodsList[position].likes = true
//            }
//            onClickBasket.invoke(foodsList[position])
//        }
    }

    override fun getItemCount(): Int {
        return foodsList.size
    }

}