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
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.osvin.foodapp.R
import com.osvin.foodapp.data.firestore.Firestore
import com.osvin.foodapp.data.models.User
import com.osvin.foodapp.databinding.FragmentLoginBinding
import com.osvin.foodapp.presentation.activity.ContainerMainActivity

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val TAG = "LoginFragment"
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        val email = binding.edtLoginAl.toString()
        val password = binding.edtPasswordAl.toString()
        binding.bLoginAl.setOnClickListener {
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            if(verifiedEmail(auth.currentUser)){
                                Firestore().getUserDetailis(this)
                            }
                        }else{
                            Log.e(TAG, "onViewCreated: ${it.exception}")
                        }
                    }
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

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        val user = User(email = currentUser?.email.toString())
        if(currentUser != null){
            Firestore().getUserDetailis(this)
        }
    }

    private fun hideKeyboardFrom(context: Context, view: View?) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun verifiedEmail(currentUser: FirebaseUser?): Boolean {
        var result = false
        if (currentUser != null) {
            result = if (currentUser.isEmailVerified) {
                true
            } else {
                Toast.makeText(context, "Please verify your email", Toast.LENGTH_SHORT).show()
                false
            }
        }
        return result
    }

    fun userLoggedInSuccess(user: User){
        Log.i(TAG, "userLoggedInSuccess: ${user.email}")
        startActivity(Intent(context, ContainerMainActivity::class.java))
        activity?.finish()
    }
}

