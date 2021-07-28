package com.captaindeer.rookmotionchallengemvvm.ui.registry.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.captaindeer.rookmotionchallengemvvm.databinding.ActivityRegistryBinding
import com.google.firebase.auth.FirebaseAuth

class RegistryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            if (binding.etEmailNew.text.isNullOrEmpty() || binding.etNameNew.text.isNullOrEmpty() || binding.etPasswordNew.text.isNullOrEmpty() || binding.etRpasswordNew.text.isNullOrEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else if (binding.etPasswordNew.text.toString() != binding.etRpasswordNew.text.toString()) {
                Toast.makeText(this, "The password doesnt match", Toast.LENGTH_SHORT).show()
            } else {
                newUser(
                    binding.etEmailNew.text.toString(),
                    binding.etPasswordNew.text.toString()
                )
                finish()
            }
        }
    }

    private fun newUser(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
    }
}