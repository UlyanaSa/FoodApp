package com.osvin.foodapp.domain.usecase

import android.util.Log
import android.util.Patterns
import android.widget.Toast


//class ValidateRegisterData(){
//    var isValid: Boolean = false
//    private val TAG = "ValidateRegisterData"
//    fun validateName():Boolean{
//        val regexName = Regex("[a-z,A-Z]")
//        if(registerUser.name.isNotEmpty()){
////            if (regexName.matches(registerUser.name)){
////                editRegisterUserLayout.emailLayout.isErrorEnabled = false
////                isValid = true
////            }else{
////                editRegisterUserLayout.nameLayout.isErrorEnabled = true
////                editRegisterUserLayout.nameLayout.error = "Name is empty"
////            }
//
////                if(registerUser.name.length <= 3){
////                    editRegisterUserLayout.nameLayout.isErrorEnabled = true
////                    editRegisterUserLayout.nameLayout.error = "Name too small"
////                    isValid = false
////                } else if (registerUser.name.length>=13){
////                    Log.d(TAG, "validateName: ${registerUser.name.length}")
////                    editRegisterUserLayout.nameLayout.isErrorEnabled = true
////                    editRegisterUserLayout.nameLayout.error = "Name too big"
////                    isValid = false
////                } else{
////                    isValid = true
////                }
//            }
//        return isValid
//    }
//
//    fun validateEmail():Boolean {
//        if (!Patterns.EMAIL_ADDRESS.matcher(registerUser.email).matches()){
//            editRegisterUserLayout.emailLayout.isErrorEnabled = true
//            editRegisterUserLayout.emailLayout.error = "Input your correct email"
//            isValid = false
//        }else{
//            editRegisterUserLayout.emailLayout.isErrorEnabled = false
//            isValid = true
//        }
//
//    return isValid
//    }
//
//}