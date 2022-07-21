package com.osvin.foodapp.presentation.fragment.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.osvin.foodapp.R
import com.osvin.foodapp.data.models.User
import com.osvin.foodapp.data.repository.AppRepository
import com.osvin.foodapp.databinding.FragmentAuthorizationBinding
import com.osvin.foodapp.presentation.activity.ContainerMainActivity
import com.osvin.foodapp.presentation.viewModel.AuthViewModel
import com.osvin.foodapp.presentation.viewModel.AuthViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class AuthorizationFragment : Fragment() {

    private lateinit var binding:FragmentAuthorizationBinding
    private lateinit var authViewModel: AuthViewModel
    private val TAG = "AuthorizationFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthorizationBinding.inflate(inflater,container,false)

        var repository: AppRepository
        val app = activity!!.application
        repository = AppRepository(app)
        authViewModel = ViewModelProvider(this, AuthViewModelFactory(app, repository))[AuthViewModel::class.java]

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)


        authViewModel.getCurrentUser()
        authViewModel.resultCurrentUser.observe(viewLifecycleOwner, Observer{
            if(it){
                startActivity(Intent(context, ContainerMainActivity::class.java))
                activity?.finish()
            }
        })

        binding.bLoginGs.setOnClickListener {
            findNavController().navigate(R.id.action_authorization_to_login)
        }
        binding.bRegisterGs.setOnClickListener {
            findNavController().navigate(R.id.action_authorization_to_register)
        }

    }



}