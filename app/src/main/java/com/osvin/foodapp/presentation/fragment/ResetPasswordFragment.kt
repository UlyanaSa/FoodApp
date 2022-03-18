package com.osvin.foodapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.osvin.foodapp.R
import com.osvin.foodapp.databinding.FragmentResetPasswordBinding


class ResetPasswordFragment : Fragment() {
private lateinit var binding: FragmentResetPasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetPasswordBinding.inflate(inflater,container,false)
        binding.bResetPasswordAr.setOnClickListener {
            findNavController().navigate(R.id.action_resetPassword_to_emailSent)
        }
        return binding.root
    }


}