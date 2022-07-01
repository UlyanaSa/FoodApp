package com.osvin.foodapp.presentation.fragment.auth

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.osvin.foodapp.R
import com.osvin.foodapp.data.models.User
import com.osvin.foodapp.databinding.FragmentRegisterBinding
import com.osvin.foodapp.data.repository.AuthorizationRepository
import com.osvin.foodapp.presentation.viewModel.AuthViewModel
import com.osvin.foodapp.presentation.viewModel.AuthViewModelFactory

//@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding:FragmentRegisterBinding
    private lateinit var progressDialog: Dialog
    private lateinit var authViewModelFactory: AuthViewModelFactory
    private lateinit var authViewModel: AuthViewModel
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
        var app = activity!!.application
        authViewModel = ViewModelProvider(this, AuthViewModelFactory(app)).get(AuthViewModel::class.java)

        val editPhone = binding.edtPhone
        validatePhone(editPhone)
        var name = binding.edtNameAc.text.toString().trim(' ')
        var email = binding.edtEmailAc.text.toString().trim(' ')
        var phone = binding.edtPhone.text.toString().trim(' ')
        var password = binding.edtPasswordAr.text.toString().trim(' ')

        var user = User(email = email, name = name, phone = phone, password = password)
        authViewModel.register(user)
        authViewModel.resultRegister.observe(viewLifecycleOwner, Observer{
            if(it){
                findNavController().navigate(R.id.action_register_to_login)
            }else Log.e(TAG, "onViewCreated: register", )
        })
                //убираем фокус с объектов ввода
        binding.bRegisterAr.setOnClickListener {
            binding.edtNameAc.clearFocus()
            binding.edtEmailAc.clearFocus()
            binding.edtPasswordAr.clearFocus()
            binding.edtPhone.clearFocus()


            }


        binding.tLogin.setOnClickListener {
           findNavController().navigate(R.id.action_register_to_login)
        }
        // при нажатии на пустое место, скрываем клавиатуру
        binding.mainLayout.setOnClickListener {
            hideKeyboardFrom(it.context,it)
        }

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



    private fun hideKeyboardFrom(context: Context, view: View?) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    fun showProgressDialog(context: Context) {
        progressDialog = Dialog(context)
        progressDialog.setContentView(R.layout.dialog_progress)
        progressDialog.setCancelable(false) // нельзя избавиться нажатие на него
        progressDialog.setCanceledOnTouchOutside(false) // снаружи
        progressDialog.show()
    }
    fun hideProgressDialog() {
        progressDialog.dismiss()
    }

    companion object {
        const val TAG = "RegisterFragment"

    }

}

