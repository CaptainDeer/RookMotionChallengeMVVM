package com.captaindeer.rookmotionchallengemvvm.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.captaindeer.rookmotionchallengemvvm.databinding.FragmentHomeBinding
import com.captaindeer.rookmotionchallengemvvm.ui.home.adapter.HomeAdapter
import com.captaindeer.rookmotionchallengemvvm.ui.home.viewmodel.HomeViewModel

class HomeFragment : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HomeAdapter
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = HomeViewModel(requireActivity().application)
        viewModel.downloadData()

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
        //viewModel
        viewModel.getAllUsersObserver().observe(requireActivity(), Observer {
            adapter.setDataList(ArrayList(it))
            adapter.notifyDataSetChanged()
        })
        //SearchView
        binding.searchView.setOnQueryTextListener(this)
    }

    private fun initRecyclerView() {
        adapter = HomeAdapter()
        binding.rvHome.setHasFixedSize(true)
        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHome.adapter = adapter
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
