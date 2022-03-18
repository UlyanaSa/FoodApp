package com.osvin.foodapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.osvin.foodapp.R
import com.osvin.foodapp.databinding.FragmentEmailSentBinding
import io.grpc.NameResolver


class EmailSentFragment : Fragment() {
    private lateinit var binding: FragmentEmailSentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmailSentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email: EmailSentFragmentArgs by navArgs()
        binding.tEmail.text = email.toString()
        val auth = FirebaseAuth.getInstance().signInWithEmailAndPassword()


        val url = "http://www.google.com/verify?uid=" + user.uid
        val actionCodeSettings = ActionCodeSettings.newBuilder()
            .setUrl(url)
            .setIOSBundleId("com.example.ios")
            // The default for this is populated with the current android package name.
            .setAndroidPackageName("com.example.android", false, null)
            .build()

        user.sendEmailVerification(actionCodeSettings)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                }
            }

        binding.tResent.setOnClickListener {
            //заново отправить письмо с кодом на почту
            //обновить таймер
        }

        binding.bLoginAe.setOnClickListener {
            findNavController().navigate(R.id.action_emailSent_to_login)

        }

    }
    private fun timer(){

    }

}