package com.osvin.foodapp.presentation.fragment.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.osvin.foodapp.R
import com.osvin.foodapp.databinding.FragmentForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding
    private val TAG = "ForgotPasswordFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotPasswordBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val email = binding.edtLoginAf.text.toString()
        val auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(context,"Successful password reset", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"Please open email", Toast.LENGTH_SHORT).show()
            }
        }
        binding.bLoginAf.setOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
            }
        }


}

