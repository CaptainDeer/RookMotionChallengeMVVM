package com.captaindeer.rookmotionchallengemvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.captaindeer.rookmotionchallengemvvm.R
import com.captaindeer.rookmotionchallengemvvm.databinding.FragmentRegistryBinding

class RegistryFragment : Fragment() {

    private var _binding: FragmentRegistryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistryBinding.inflate(inflater, container, false)

        //Navigation
        binding.btnSubmit.setOnClickListener { view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.registryFragment_to_logFragment) } }
        return binding.root
    }

}