package com.example.partnersapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.partnersapp.databinding.RcItemCategoryBinding
import com.example.partnersapp.model.partnerModels.category.CategoryNew


class AdapterCategoryNew:RecyclerView.Adapter<AdapterCategoryNew.MyHolder>() {

    private var listItem:  MutableList<CategoryNew> = mutableListOf()

    class MyHolder(private val binding: RcItemCategoryBinding):RecyclerView.ViewHolder(binding.root) {

           fun bind(catNew: CategoryNew){
              setName(catNew.categoryName)
              setImage(catNew.categoryImage)
              setCount(catNew.categoryCount)

           }
        @SuppressLint("SetTextI18n")
        private fun setCount(categoryCount: Int) {
            if (categoryCount == 1) {
                binding.countPartners.text = "$categoryCount предложение"
            }
            if (categoryCount in 2..4) {
                binding.countPartners.text = "$categoryCount предложения"
            }
            if (categoryCount >= 5 ) {
                binding.countPartners.text = "$categoryCount предложений"
            }
            if (categoryCount == 0){
                binding.countPartners.text = "нет предложений"
            }
        }

        private fun setImage(categoryImage: Int) {
            Glide.with(itemView.context).load(categoryImage).into(binding.categoryImage)
        }

        private fun setName(name: String) {
            binding.categoryName.text = name
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            RcItemCategoryBinding.inflate(
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
    fun setListNCateg(list: List<CategoryNew>) {
        listItem.addAll(list)
        notifyDataSetChanged()
    }
}