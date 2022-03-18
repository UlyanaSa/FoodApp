package com.osvin.foodapp.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.osvin.foodapp.R
import com.osvin.foodapp.databinding.FragmentAuthorizationBinding


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
       // binding = FragmentAuthorizationBinding.bind(view) // передаем нашу разметку
        Log.d(TAG, "onViewCreated: ")
        binding.bLoginGs.setOnClickListener {
            findNavController().navigate(R.id.action_authorization_to_login)
        }
        binding.bRegisterGs.setOnClickListener {
            findNavController().navigate(R.id.action_authorization_to_register)
        }

    }



}