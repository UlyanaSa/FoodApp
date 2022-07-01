package com.osvin.foodapp.presentation.activity


import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.osvin.foodapp.R
import dagger.hilt.android.AndroidEntryPoint


//@AndroidEntryPoint
class ContainerAuthActivity : AppCompatActivity() {
    private val TAG = "ContainerAuthActivity"
   // private lateinit var binding: ActivityContainerAuthBinding
    // не работает навигация норм с вьюбандингом
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        //binding = ActivityContainerAuthBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container_auth)
        val navAuthHost = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navAuthHost.navController


    }

}