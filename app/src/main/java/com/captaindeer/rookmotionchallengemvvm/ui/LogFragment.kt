package com.captaindeer.rookmotionchallengemvvm.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.Navigation
import com.captaindeer.rookmotionchallengemvvm.R
import com.captaindeer.rookmotionchallengemvvm.databinding.FragmentLogBinding

class LogFragment : Fragment() {

    private var _binding: FragmentLogBinding ?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogBinding.inflate(inflater,container,false)

        binding.btnLog.setOnClickListener {
            //Verificar datos
            //Entrar
            startActivity(Intent(requireContext(), MainActivity::class.java))
            finishAffinity(requireActivity())
        }

        //Navigation
        binding.tvRegistry.setOnClickListener { view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.logFragment_to_registryFragment) } }
        return binding.root
    }

}