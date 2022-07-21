package com.osvin.foodapp.presentation.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.osvin.foodapp.data.repository.AppRepository
import com.osvin.foodapp.databinding.FragmentRestaurantBinding
import com.osvin.foodapp.pojo.Food
import com.osvin.foodapp.pojo.FoodCategory
import com.osvin.foodapp.pojo.FoodList
import com.osvin.foodapp.presentation.adapter.FoodByCategoryAdapter
import com.osvin.foodapp.presentation.viewModel.FoodByCategoryViewModel
import com.osvin.foodapp.presentation.viewModel.FoodItemViewModelFactory


class RestaurantFragment : Fragment() {
    private lateinit var binding: FragmentRestaurantBinding
    private lateinit var foodByCategoryViewModel: FoodByCategoryViewModel
    private lateinit var categoryName: String
    private lateinit var foodItemAdapter: FoodByCategoryAdapter

    companion object {
        const val CATEGORY_NAME = "CATEGORY_NAME"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRestaurantBinding.inflate(inflater,container,false)
        val app = activity!!.application
        val repository = AppRepository(app)
        foodByCategoryViewModel = ViewModelProvider(this)[FoodByCategoryViewModel::class.java]
        prepareFoodItemAdapter()
        return binding.root
    }

    private fun prepareFoodItemAdapter() {
        foodItemAdapter = FoodByCategoryAdapter()
        binding.recyclerView.apply{
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = foodItemAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryName = arguments?.get(CATEGORY_NAME).toString()
        foodByCategoryViewModel.getFoodItemDetail(categoryName)
        observeFoodByCategoryVM()
    }

    private fun observeFoodByCategoryVM(){
        foodByCategoryViewModel.foodItemByCategoryLiveData.observe(viewLifecycleOwner, Observer {
            binding.categoryName.text = categoryName
            foodItemAdapter.setFooList(it as ArrayList<FoodCategory>)
        })
    }
}