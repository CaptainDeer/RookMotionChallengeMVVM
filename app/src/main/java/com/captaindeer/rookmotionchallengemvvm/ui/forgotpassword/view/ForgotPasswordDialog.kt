package com.captaindeer.rookmotionchallengemvvm.ui.forgotpassword.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.captaindeer.rookmotionchallengemvvm.databinding.DialogForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordDialog : DialogFragment() {

    private var _binding: DialogForgotPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogForgotPasswordBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnAccept.setOnClickListener {
            if (binding.etEmailReset.text.isNullOrBlank()) {
                Toast.makeText(requireContext(), "Please type your e-mail", Toast.LENGTH_SHORT)
                    .show()
            } else {
                FirebaseAuth.getInstance()
                    .sendPasswordResetEmail(binding.etEmailReset.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            dismiss()
                            Toast.makeText(
                                requireContext(),
                                "Verify your E-mail",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                requireContext(),
                                task.exception.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

}