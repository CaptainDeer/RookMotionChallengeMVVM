package com.captaindeer.rookmotionchallengemvvm.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.captaindeer.rookmotionchallengemvvm.data.local.entities.UserEntity
import com.captaindeer.rookmotionchallengemvvm.databinding.ItemUserBinding
import com.captaindeer.rookmotionchallengemvvm.ui.home.view.HomeFragmentDirections
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var items = ArrayList<UserEntity>()

    fun setDataList(data: ArrayList<UserEntity>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        val inflater =
            LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.navigateToDetailFragment(items[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int = items.size

    class HomeViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: UserEntity) {
            binding.userEntity = data
            binding.executePendingBindings()
        }
    }

    companion object {
        @JvmStatic // add this line !!
        @BindingAdapter("loadImage")
        fun loadImage(ivUser: CircleImageView, url: String) {
            Picasso.get().load(url).into(ivUser)
        }
    }
}