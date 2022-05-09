package com.osvin.foodapp.presentation.activity

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.osvin.foodapp.R



class ContainerMainActivity : AppCompatActivity() {

    private val TAG = "ContainerMainActivity"
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container_main)
        val navMainHost =supportFragmentManager.findFragmentById(R.id.fragment_container_menu) as NavHostFragment
        navController = navMainHost.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_home_menu)
        bottomNavigationView.setupWithNavController(navController)
    }


}