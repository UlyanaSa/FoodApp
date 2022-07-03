package com.osvin.foodapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.osvin.foodapp.databinding.ListFoodsBinding
import com.osvin.foodapp.pojo.Food
import com.osvin.foodapp.pojo.FoodList
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

class RecommendationAdapter(): RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder>() {

    private var foodsList = ArrayList<Food>()
    fun setFoods(foodsList: ArrayList<Food>){
        this.foodsList = foodsList
        notifyDataSetChanged()
    }
    lateinit var onClickItem: ((Food)->Unit)


    class RecommendationViewHolder(val binding: ListFoodsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        return RecommendationViewHolder(ListFoodsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.binding.tNameFood.text = foodsList[position].strMeal
        holder.binding.tPrice.text = ThreadLocalRandom.current().nextInt(100).toString()+"$"
        Glide.with(holder.itemView)
            .load(foodsList[position].strMealThumb)
            .into(holder.binding.imageFood)
        holder.itemView.setOnClickListener {
            onClickItem.invoke(foodsList[position])
        }
    }

    override fun getItemCount(): Int {
        return foodsList.size
    }
}