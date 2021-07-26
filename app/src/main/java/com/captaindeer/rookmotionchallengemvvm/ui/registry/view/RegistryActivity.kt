package com.captaindeer.rookmotionchallengemvvm.ui.registry.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.captaindeer.rookmotionchallengemvvm.databinding.ActivityRegistryBinding

class RegistryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}