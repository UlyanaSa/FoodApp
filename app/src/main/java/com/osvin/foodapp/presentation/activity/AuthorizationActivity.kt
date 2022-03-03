package com.osvin.foodapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.osvin.foodapp.R
import com.osvin.foodapp.databinding.ActivityAuthorizationBinding

class AuthorizationActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthorizationBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)



    }
}