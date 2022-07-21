package com.osvin.foodapp.presentation.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.osvin.foodapp.R
import com.osvin.foodapp.data.db.FoodDatabase
import com.osvin.foodapp.data.repository.AppRepository
import com.osvin.foodapp.databinding.FragmentHomeBinding
import com.osvin.foodapp.pojo.Category
import com.osvin.foodapp.pojo.Food
import com.osvin.foodapp.pojo.FoodCategory
import com.osvin.foodapp.presentation.adapter.CategoriesAdapter
import com.osvin.foodapp.presentation.adapter.RecommendationAdapter
import com.osvin.foodapp.presentation.viewModel.HomeViewModel
import com.osvin.foodapp.presentation.viewModel.HomeViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recommendationAdapter: RecommendationAdapter
    private lateinit var categoryListAdapter: CategoriesAdapter

    companion object {
        const val FOOD_NAME = "FOOD_NAME"
        const val FOOD_IMAGE = "FOOD_IMAGE"
        const val FOOD_PRICE = "FOOD_PRICE"
        const val FOOD_ID = "FOOD_ID"

        const val CATEGORY_NAME = "CATEGORY_NAME"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = activity!!.application
        val repository = AppRepository(app)

        val foodDatabase: FoodDatabase = FoodDatabase.getInstance(context!!)
        homeViewModel = ViewModelProvider(this, HomeViewModelFactory(app, repository))[HomeViewModel::class.java]
        recommendationAdapter = RecommendationAdapter()
        categoryListAdapter = CategoriesAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareFoodItemsRecyclerView()
        prepareCategoryRecyclerView()

        homeViewModel.getFoodItems()
        homeViewModel.getCategoryItems()
        observeFoodItems()
        observeCategoryItems()

        onRecomendationItemClick()
        onCategoryItemClick()

    }

    private fun onCategoryItemClick() {
        categoryListAdapter.onClickItem = {
            val bundle = Bundle()
            bundle.putString(CATEGORY_NAME, it.strCategory)
            findNavController().navigate(R.id.action_homeFragment_to_restaurantFragment, bundle)
        }
    }

    private fun prepareCategoryRecyclerView() {
        categoryListAdapter = CategoriesAdapter()
        binding.rvListRestaurants.apply {
            layoutManager = GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false )
            adapter = categoryListAdapter

        }
    }

    private fun observeCategoryItems() {
        homeViewModel.categoryListLiveData.observe(viewLifecycleOwner, Observer {
            categoryListAdapter.setCategory(it as ArrayList<Category>)
        })
    }

    private fun onRecomendationItemClick() {
        recommendationAdapter.onClickItem = {
           var bundle = Bundle()
            bundle.putString(FOOD_NAME, it.strMeal)
            bundle.putString(FOOD_IMAGE, it.strMealThumb)
            bundle.putString(FOOD_PRICE, it.price)
            bundle.putString(FOOD_ID, it.idMeal)
          findNavController().navigate(R.id.action_homeFragment_to_foodItemFragment, bundle)
        }
    }

    private fun prepareFoodItemsRecyclerView() {
        binding.rvListFood.apply {
            adapter = recommendationAdapter
        }
    }

    private fun observeFoodItems() {
        homeViewModel.foodListLiveData.observe(viewLifecycleOwner,  Observer{
            recommendationAdapter.setFoods(it as ArrayList<FoodCategory>)
        })
    }


}