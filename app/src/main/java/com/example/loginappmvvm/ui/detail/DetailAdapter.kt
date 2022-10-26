package com.example.loginappmvvm.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.loginappmvvm.R
import com.example.loginappmvvm.database.RegisterEntity
import com.example.loginappmvvm.databinding.ItemDetailBinding

class DetailAdapter(private val usersList :List<RegisterEntity>): RecyclerView.Adapter<DetailAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemDetailBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_detail,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(usersList[position])
    }

    override fun getItemCount(): Int = usersList.size


    class MyViewHolder(private val binding :ItemDetailBinding ):RecyclerView.ViewHolder(binding.root) {
        fun bind(user : RegisterEntity){
            binding.tvEmailDetails.text = user.email
        }
    }
}