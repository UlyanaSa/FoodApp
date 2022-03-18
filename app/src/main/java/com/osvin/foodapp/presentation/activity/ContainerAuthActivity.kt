package com.osvin.foodapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.osvin.foodapp.R
import com.osvin.foodapp.databinding.ActivityContainerAuthBinding



class ContainerAuthActivity : AppCompatActivity() {
   // private lateinit var binding: ActivityContainerAuthBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        //binding = ActivityContainerAuthBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container_auth)
        var navAuthHost = supportFragmentManager.findFragmentById(R.id.fragment_nav_host) as NavHostFragment
        navController = navAuthHost.navController

     //   Navigation.setViewNavController(,navController)
      //  NavigationUI.setupActionBarWithNavController(this,navController)


    }


}