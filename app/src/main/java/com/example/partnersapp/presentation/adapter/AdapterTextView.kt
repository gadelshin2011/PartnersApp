package com.example.partnersapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.partnersapp.databinding.RcItemTvBinding
import com.example.partnersapp.model.partnerModels.TextViewModel



class AdapterTextView:RecyclerView.Adapter<AdapterTextView.MyHolder>() {

    private var listItem:  MutableList<TextViewModel> = mutableListOf()

    class MyHolder(private val binding: RcItemTvBinding):RecyclerView.ViewHolder(binding.root) {

           fun bind(tv: TextViewModel){
              setName(tv.allOffers)
           }

        private fun setName(allOffers: String) {
            binding.tvAllPartners.text = allOffers
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            RcItemTvBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
       holder.bind(listItem[position])
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setListTV(list: List<TextViewModel>) {
        listItem.addAll(list)
        notifyDataSetChanged()
    }
}