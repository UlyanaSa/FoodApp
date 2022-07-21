package com.osvin.foodapp.presentation.fragment.home

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.osvin.foodapp.R
import com.osvin.foodapp.data.db.FoodDatabase
import com.osvin.foodapp.databinding.FragmentFoodItemBinding
import com.osvin.foodapp.pojo.FoodCategory
import com.osvin.foodapp.presentation.viewModel.FoodItemViewModel
import com.osvin.foodapp.presentation.viewModel.FoodItemViewModelFactory


class FoodItemFragment : Fragment() {
    private lateinit var binding: FragmentFoodItemBinding
    companion object {
        const val FOOD_NAME = "FOOD_NAME"
        const val FOOD_IMAGE = "FOOD_IMAGE"
        const val FOOD_PRICE = "FOOD_PRICE"
        const val FOOD_ID = "FOOD_ID"
        const val TAG = "FoodItemFragment"

    }
    private lateinit var nameFood: String
    private lateinit var imageFood: String
    private lateinit var priceFood: String
    private lateinit var idFood: String
    private lateinit var foodItemViewModel: FoodItemViewModel
    private lateinit var foodDatabase: FoodDatabase
    private val foodToSave by lazy {FoodCategory(strMeal = "", strMealThumb = "", idMeal = "")}



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFoodItemBinding.inflate(inflater,container,false )
//        val app = activity!!.application
//        val repository = AppRepository(app)
        foodDatabase = FoodDatabase.getInstance(context!!)
        foodItemViewModel = ViewModelProvider(this, FoodItemViewModelFactory(foodDatabase))[FoodItemViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFoodInfo()
        getImageFoodItem()
        foodItemViewModel.getFoodItemDetail(idFood)

        observerFoodItemLiveData()
        observerFoodDBLiveData()
        observerFoodCountLiveData()

        binding.tNameFood.text = nameFood
        binding.tPrice.text = priceFood

        binding.bLikeFood.setOnClickListener {
            foodItemViewModel.insertFood(foodToSave)
            Toast.makeText(context, "Add food ${foodToSave.strMeal} to likes", Toast.LENGTH_SHORT).show()
        }

        onClickBasket()


        binding.bLikeFood.setOnClickListener {
            if(foodToSave.likes) {
                binding.bLikeFood.setBackgroundColor(R.color.primary)
                foodToSave.likes = false
            }else{
                binding.bLikeFood.setBackgroundColor(Color.RED)
                foodToSave.likes = true
            }
        }
        val countFood :Int = binding.tCountFood.text.toString().toInt()

        binding.bMinus.setOnClickListener {
           foodItemViewModel.minus(countFood)
        }

        binding.bPlus.setOnClickListener {
            foodItemViewModel.plus(countFood)
        }


    }

    private fun observerFoodCountLiveData() {
        foodItemViewModel.foodCount.observe(viewLifecycleOwner, Observer{
            binding.tCountFood.text = it.toString()
        })
    }

    private fun onClickBasket() {
        binding.bAddToBasket.setOnClickListener {
            foodToSave.basket = true
            foodItemViewModel.insertFood(foodToSave)
            Toast.makeText(context, "Add food ${foodToSave.strMeal} to basket", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observerFoodDBLiveData() {
        foodItemViewModel.foodDBLiveData.observe(viewLifecycleOwner, Observer{
            foodToSave.strMeal = nameFood
            foodToSave.idMeal = idFood
            foodToSave.strMealThumb = imageFood
            foodToSave.price = priceFood
            foodToSave.basket = it.basket
            foodToSave.likes = it.likes
        })
    }

    private fun observerFoodItemLiveData() {
        foodItemViewModel.foodItemLiveData.observe(viewLifecycleOwner, Observer {
            binding.tInfoFood.text = it.strInstructions
            binding.tNameRestaurant.text = "Category: ${it.strCategory}"
        })
    }

    fun getFoodInfo(){
        nameFood = arguments?.getString(FOOD_NAME, "default food") ?: "default food"
        imageFood = arguments?.getString(FOOD_IMAGE, "default image") ?: "default image"
        priceFood = arguments?.getString(FOOD_PRICE, "0$") ?: "0$"
        idFood = arguments?.getString(FOOD_ID, "id") ?: "id"
    }

    fun getImageFoodItem(){
        Glide.with(activity!!.applicationContext)
            .load(imageFood)
            .into(binding.imageFood)
    }




}