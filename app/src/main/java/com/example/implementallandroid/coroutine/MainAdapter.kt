package com.example.implementallandroid.coroutine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.implementallandroid.databinding.CoroutineItemListBinding

class MainAdapter(private val users: ArrayList<User>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return users.size
    }

    class ItemListViewHolder(var viewBinding: CoroutineItemListBinding) : RecyclerView.ViewHolder(viewBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CoroutineItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as ItemListViewHolder
        itemViewHolder.viewBinding.textViewUserName.text = users[position].userName
        itemViewHolder.viewBinding.textViewUserEmail.text = users[position].userEmail
        Glide.with(itemViewHolder.viewBinding.imageViewAvatar.context)
            .load(users[position].image)
            .into(itemViewHolder.viewBinding.imageViewAvatar)
    }
    fun addUsers(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }
}