package com.captaindeer.rookmotionchallengemvvm.ui.userdetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.captaindeer.rookmotionchallengemvvm.R
import com.captaindeer.rookmotionchallengemvvm.data.local.entities.UserEntity
import com.captaindeer.rookmotionchallengemvvm.databinding.FragmentUserDetailBinding
import com.captaindeer.rookmotionchallengemvvm.ui.userdetail.viewmodel.UserDetailViewModel
import com.squareup.picasso.Picasso

class UserDetailFragment : Fragment() {

    private val args: UserDetailFragmentArgs by navArgs()
    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: UserDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = UserDetailViewModel(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailBinding.inflate(layoutInflater)

        val id = args.userId
        setUserInfo(viewModel.getUser(id))

        //Navigation
        binding.btnBack.setOnClickListener {
            view?.let { it1 ->
                Navigation.findNavController(it1).navigate(R.id.navigateToHomeFragment)
            }
        }
        return binding.root
    }

    private fun setUserInfo(userEntity: UserEntity) {
        binding.tvUserId.text = userEntity.id.toString()
        binding.tvUserName.text = userEntity.first_name
        binding.tvUserLastName.text = userEntity.last_name.toString()
        binding.tvUserEmail.text = userEntity.email
        Picasso.get().load(userEntity.avatar).into(binding.ivUserDetail)
    }

}