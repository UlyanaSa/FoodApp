package com.osvin.foodapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.osvin.foodapp.databinding.ListRestaurantsBinding
import com.osvin.foodapp.pojo.Category



class CategoriesAdapter(): RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private var categoriesList = ArrayList<Category>()
    fun setCategory(foodsList: ArrayList<Category>){
        this.categoriesList = foodsList
        notifyDataSetChanged()
    }
    var onClickItem: ((Category)->Unit)? = null

    class CategoriesViewHolder(val binding: ListRestaurantsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
            return CategoriesViewHolder(
                ListRestaurantsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.binding.categoryName.text = categoriesList[position].strCategory
        Glide.with(holder.itemView)
            .load(categoriesList[position].strCategoryThumb)
            .into(holder.binding.imageLogo)
        holder.itemView.setOnClickListener {
            onClickItem?.invoke(categoriesList[position])
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }
}