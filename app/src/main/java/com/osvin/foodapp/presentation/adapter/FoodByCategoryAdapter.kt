package com.osvin.foodapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.osvin.foodapp.databinding.ListFoodsBinding
import com.osvin.foodapp.pojo.CategoryList
import com.osvin.foodapp.pojo.Food
import com.osvin.foodapp.pojo.FoodCategory
import com.osvin.foodapp.pojo.FoodList
import com.osvin.foodapp.presentation.viewModel.FoodItemViewModel
import java.util.concurrent.ThreadLocalRandom

class FoodByCategoryAdapter: RecyclerView.Adapter<FoodByCategoryAdapter.FoodByCategoryViewModel>() {


    private var foodByCategoryList = ArrayList<FoodCategory>()
    fun setFooList(foodByCategoryList: ArrayList<FoodCategory>){
        this.foodByCategoryList = foodByCategoryList
        notifyDataSetChanged()
    }



    inner class FoodByCategoryViewModel(val binding: ListFoodsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodByCategoryViewModel {
        return FoodByCategoryViewModel(ListFoodsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FoodByCategoryViewModel, position: Int) {
        Glide.with(holder.itemView)
            .load(foodByCategoryList[position].strMealThumb)
            .into(holder.binding.imageFood)
        holder.binding.tNameFood.text = foodByCategoryList[position].strMeal
        holder.binding.tPrice.text = ThreadLocalRandom.current().nextInt(100).toString()+"$"
      //  foodByCategoryList[position].price = holder.binding.tPrice.text as String

    }

    override fun getItemCount(): Int {
        return foodByCategoryList.size
    }
}