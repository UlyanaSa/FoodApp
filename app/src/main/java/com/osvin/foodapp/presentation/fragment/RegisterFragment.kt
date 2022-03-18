package com.osvin.foodapp.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.osvin.foodapp.R
import com.osvin.foodapp.databinding.FragmentAuthorizationBinding
import com.osvin.foodapp.databinding.FragmentRegisterBinding
import com.osvin.foodapp.domain.models.EditRegisterUserLayout
import com.osvin.foodapp.domain.models.RegisterUser
import com.osvin.foodapp.presentation.activity.ContainerAuthActivity

class RegisterFragment : Fragment() {

    private lateinit var binding:FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        val registerUser = RegisterUser(
            binding.edtNameAc.toString().trim(' '),
            binding.edtEmailAc.toString().trim(' '),
            binding.edtPhone.toString().trim(' '),
            binding.edtPasswordAr.toString().trim(' ')
        )
        val editRegisterUserLayout = EditRegisterUserLayout(
            binding.edtNameLayout,
            binding.edtEmailLayout,
            binding.edtPhoneLayout,
            binding.edtPasswordLayout
        )
        val editPhone = binding.edtPhone
        binding.bRegisterAr.setOnClickListener {
            if(validateRegisterData(registerUser,editRegisterUserLayout, editPhone)){
                registerUser(registerUser)
                findNavController().navigate(R.id.action_register_to_emailSent)
            }
        }
    }



    private fun validateRegisterData(registerUser: RegisterUser,
                                     editRegisterUserLayout: EditRegisterUserLayout,
                                     editPhone: TextInputEditText):Boolean{
        var isValid = true
        val regexName = Regex("[a-z]{3,13}")
        if (regexName.matches(registerUser.name)){
            editRegisterUserLayout.emailLayout.isErrorEnabled = false
        }else{
            if(registerUser.name.length<3){
                editRegisterUserLayout.nameLayout.isErrorEnabled = true
                binding.edtNameLayout.error = "Name too small"
                isValid = false
            } else if (registerUser.name.length>13){
                editRegisterUserLayout.nameLayout.isErrorEnabled = true
                editRegisterUserLayout.nameLayout.error = "Name too big"
                isValid = false
            }
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(registerUser.email).matches()){
            editRegisterUserLayout.emailLayout.isErrorEnabled = true
            editRegisterUserLayout.emailLayout.error = "Input your correct email"
            isValid = false
        }else{
            editRegisterUserLayout.emailLayout.isErrorEnabled = false
        }

        if(validatePhone(editPhone)){
            editRegisterUserLayout.phoneLayout.isErrorEnabled = false
            isValid = true
        }else{
            editRegisterUserLayout.phoneLayout.isErrorEnabled = true
            editRegisterUserLayout.phoneLayout.error = "Incorrect phone"
            isValid = false
        }
        return isValid
    }

    private fun validatePhone(editPhone:TextInputEditText):Boolean{
        var mSelfChange = false
        editPhone.addTextChangedListener(object : TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s == null || mSelfChange) {
                    return
                }

                val preparedStr = s.replace(Regex("(\\D*)"), "")
                var resultStr = ""
                for (i in preparedStr.indices) {
                    resultStr = when (i) {
                        0 -> resultStr.plus("+7 (")
                        1 -> resultStr.plus(preparedStr[i])
                        2 -> resultStr.plus(preparedStr[i])
                        3 -> resultStr.plus(preparedStr[i])
                        4 -> resultStr.plus(") ".plus(preparedStr[i]))
                        5 -> resultStr.plus(preparedStr[i])
                        6 -> resultStr.plus(preparedStr[i])
                        7 -> resultStr.plus("-".plus(preparedStr[i]))
                        8 -> resultStr.plus(preparedStr[i])
                        9 -> resultStr.plus("-".plus(preparedStr[i]))
                        10 -> resultStr.plus(preparedStr[i])
                        else -> resultStr
                    }
                }

                mSelfChange = true
                val oldSelectionPos = editPhone.selectionStart
                val isEdit = editPhone.selectionStart != editPhone.length()
                editPhone.setText(resultStr)
                if (isEdit) {
                    editPhone.setSelection(if (oldSelectionPos > resultStr.length) resultStr.length else oldSelectionPos)
                } else {
                    editPhone.setSelection(resultStr.length)
                }
                mSelfChange = false
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        return mSelfChange
    }


    private fun registerUser(registerUser: RegisterUser){
        val email = registerUser.email
        val password = registerUser.password
        if(email.isNotEmpty() && password.isNotEmpty() ){

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser? = task.result!!.user
                        Toast.makeText(context, "Successful registration", Toast.LENGTH_SHORT)
                            .show()
                        FirebaseAuth.getInstance().signOut()
                    }
                    }
                }else{
            Toast.makeText(context, "Incorrect data",Toast.LENGTH_SHORT).show()
        }
    }


}