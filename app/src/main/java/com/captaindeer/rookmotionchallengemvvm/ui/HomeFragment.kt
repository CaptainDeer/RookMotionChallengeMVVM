package com.captaindeer.rookmotionchallengemvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.captaindeer.rookmotionchallengemvvm.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //RecyclerView
        initRecyclerView()
        //SearchView
        binding.searchView.setOnQueryTextListener(this)
    }

    private fun initRecyclerView() {
        //  adapter = UsersAdapter(users)
        binding.rvHome.setHasFixedSize(true)
        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())
        //  binding.rvHome.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {

        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrBlank()) {

        }
        return false
    }
}
