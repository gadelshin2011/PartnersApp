package com.example.partnersapp.presenter.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.partnersapp.databinding.RcItemBinding
import com.example.partnersapp.model.Partner

class RcAdapterPartners:RecyclerView.Adapter<RcAdapterPartners.MyHolder>() {

    var listItem : MutableList<Partner> = mutableListOf()

    class MyHolder(private val binding: RcItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(part: Partner){

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
    fun setList(list: List<Partner>){
        listItem.addAll(list)
        notifyDataSetChanged()
    }
}