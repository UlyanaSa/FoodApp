package com.osvin.foodapp.presentation.fragment.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.osvin.foodapp.R
import com.osvin.foodapp.databinding.FragmentAuthorizationBinding
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class AuthorizationFragment : Fragment() {

    private lateinit var binding:FragmentAuthorizationBinding
    private val TAG = "AuthorizationFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthorizationBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        binding.bLoginGs.setOnClickListener {
            findNavController().navigate(R.id.action_authorization_to_login)
        }
        binding.bRegisterGs.setOnClickListener {
            findNavController().navigate(R.id.action_authorization_to_register)
        }

    }



}