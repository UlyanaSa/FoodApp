package com.osvin.foodapp.presentation.fragment.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.osvin.foodapp.R
import com.osvin.foodapp.data.firestore.Firestore
import com.osvin.foodapp.data.models.User
import com.osvin.foodapp.data.repository.AuthorizationRepository
import com.osvin.foodapp.databinding.FragmentLoginBinding
import com.osvin.foodapp.presentation.activity.ContainerMainActivity
import com.osvin.foodapp.presentation.viewModel.AuthViewModel
import com.osvin.foodapp.presentation.viewModel.AuthViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val TAG = "LoginFragment"
    private lateinit var authViewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        val app = activity!!.application
        authViewModel = ViewModelProvider(this, AuthViewModelFactory(app)).get(AuthViewModel::class.java)


        val email = binding.edtLoginAl.toString()
        val password = binding.edtPasswordAl.toString()



        binding.bLoginAl.setOnClickListener {
            if (email.isNotEmpty() && password.isNotEmpty()) {
                var user = User(email = email, password = password)
                authViewModel.login(user)
                authViewModel.resultLogin.observe(viewLifecycleOwner, Observer{
                    if(it){
                        authViewModel.verifiedEmail()
                    }else Log.e(TAG, "onViewCreated: login", )
                })
                
            }

        }
        binding.tRegister.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }
        binding.tForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        binding.mainLayout.setOnClickListener {
            hideKeyboardFrom(it.context,it)
        }
    }

    private fun hideKeyboardFrom(context: Context, view: View?) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    fun userLoggedInSuccess(user: User){
        Log.i(TAG, "userLoggedInSuccess: ${user.email}")
        startActivity(Intent(context, ContainerMainActivity::class.java))
        activity?.finish()
    }
}

