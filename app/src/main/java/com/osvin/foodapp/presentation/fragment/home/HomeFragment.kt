package com.osvin.foodapp.presentation.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.osvin.foodapp.R
import com.osvin.foodapp.data.repository.AppRepository
import com.osvin.foodapp.databinding.FragmentAuthorizationBinding
import com.osvin.foodapp.databinding.FragmentHomeBinding
import com.osvin.foodapp.pojo.Food
import com.osvin.foodapp.presentation.adapter.RecommendationAdapter
import com.osvin.foodapp.presentation.viewModel.AuthViewModel
import com.osvin.foodapp.presentation.viewModel.AuthViewModelFactory
import com.osvin.foodapp.presentation.viewModel.HomeViewModel
import com.osvin.foodapp.presentation.viewModel.HomeViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recommendationAdapter: RecommendationAdapter
    companion object {
        const val FOOD_NAME = "FOOD_NAME"
        const val FOOD_IMAGE = "FOOD_IMAGE"
        const val FOOD_PRICE = "FOOD_PRICE"
        const val FOOD_INFO = "FOOD_INFO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = activity!!.application
        val repository = AppRepository(app)
        homeViewModel = ViewModelProvider(this, HomeViewModelFactory(app, repository))[HomeViewModel::class.java]
        recommendationAdapter = RecommendationAdapter()
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
        homeViewModel.getFoodItems()
        observeFoodItems()
        onRecomendationItemClick()


    }

    private fun onRecomendationItemClick() {
        recommendationAdapter.onClickItem = {
            var bundle = Bundle()
            val food = Food(strMeal = it.strMeal, strMealThumb = it.strMealThumb)
            bundle.putBundle(FOOD_NAME, )
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
            recommendationAdapter.setFoods(foodsList = it)
        })
    }


}