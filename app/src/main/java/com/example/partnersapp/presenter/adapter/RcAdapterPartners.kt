package com.example.partnersapp.presenter.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.partnersapp.databinding.RcItemBinding
import com.example.partnersapp.model.authData

class RcAdapterPartners:RecyclerView.Adapter<RcAdapterPartners.MyHolder>() {

    var listItem : MutableList<authData> = mutableListOf()

    class MyHolder(private val binding: RcItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(auth: authData){

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            RcItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: MutableList<authData>){
        listItem = list
        notifyDataSetChanged()
    }
}