package com.osvin.foodapp.presentation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.osvin.foodapp.R
import com.osvin.foodapp.databinding.FragmentLoginBinding
import com.osvin.foodapp.domain.models.LoginUser
import com.osvin.foodapp.presentation.activity.HomeActivity

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        val firebaseAuth = FirebaseAuth.getInstance()
        val loginUser = LoginUser(
            binding.edtLoginAl.toString(),
            binding.edtPasswordAl.toString()
        )

        binding.bLoginAl.setOnClickListener {
            firebaseAuth.signInWithEmailAndPassword(loginUser.login,loginUser.password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    if(firebaseAuth.currentUser!!.isEmailVerified){
                        val intent = Intent(context,HomeActivity::class.java)
                    }else{
                        Toast.makeText(context,"Please verify your email",Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_register_to_emailSent)
                    }
                }else{

                }

            }
        }
        binding.tRegister.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }
        binding.tForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_resetPassword)
        }


    }



}

