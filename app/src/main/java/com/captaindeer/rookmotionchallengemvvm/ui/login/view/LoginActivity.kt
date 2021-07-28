package com.captaindeer.rookmotionchallengemvvm.ui.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.captaindeer.rookmotionchallengemvvm.databinding.ActivityLoginBinding
import com.captaindeer.rookmotionchallengemvvm.ui.forgotpassword.ForgotPasswordDialog
import com.captaindeer.rookmotionchallengemvvm.ui.login.viewmodel.LoginViewModel
import com.captaindeer.rookmotionchallengemvvm.ui.main.view.MainActivity
import com.captaindeer.rookmotionchallengemvvm.ui.registry.view.RegistryActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel.progressVisibility.observe(this, Observer {
            binding.loginProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        loginViewModel.logged.observe(this, Observer {
            if (it) {
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            }
        })




        binding.btnLog.setOnClickListener {
            if (binding.etEmail.text.isNullOrEmpty() || binding.etPassword.text.isNullOrEmpty())
                Toast.makeText(this, "Type an email or password", Toast.LENGTH_SHORT).show()
            else loginViewModel.logIn(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }

        binding.tvRegistry.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    RegistryActivity::class.java
                )
            )
        }

        binding.forgot.setOnClickListener {
            ForgotPasswordDialog().show(supportFragmentManager, "customDialog")
        }
    }


    override fun onStart() {
        super.onStart()
        loginViewModel.logOn()

    }
}