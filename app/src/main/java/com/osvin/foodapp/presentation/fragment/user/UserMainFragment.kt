package com.osvin.foodapp.presentation.fragment.user

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.osvin.foodapp.databinding.FragmentUserMainBinding


class UserMainFragment : Fragment() {

    private lateinit var binding: FragmentUserMainBinding

    var getImage = registerForActivityResult(ActivityResultContracts.GetContent(), ActivityResultCallback{
        //  binding.userPhoto.setImageURI(it)
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun selestPhoto(){
        getImage.launch("image/*")
    }




}