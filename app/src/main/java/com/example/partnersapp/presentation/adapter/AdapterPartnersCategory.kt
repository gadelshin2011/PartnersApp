package com.example.partnersapp.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.partnersapp.databinding.RcItemCategoryBinding
import com.example.partnersapp.model.partnerModels.category.PartnerCategoryDetail


class AdapterPartnersCategory(private val itemClickListener: (PartnerCategoryDetail) -> Unit) :
    RecyclerView.Adapter<AdapterPartnersCategory.MyHolder>() {

    private var listItemCategory: MutableList<PartnerCategoryDetail> = mutableListOf()

    class MyHolder(private val binding: RcItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(partCat: PartnerCategoryDetail) {
            setColor(partCat.color)
            setImage(partCat.imageColor)
            setCountPartners(partCat.countPartners)
            setNameCategory(partCat.title)

        }


        private fun setNameCategory(name: String) {
            binding.categoryName.text = name
        }

        @SuppressLint("SetTextI18n")
        private fun setCountPartners(countPartners: Int) {
            if (countPartners == 1) {
                binding.countPartners.text = "$countPartners предложение"
            }
            if (countPartners in 2..4) {
                binding.countPartners.text = "$countPartners предложения"
            }
            if (countPartners >= 5) {
                binding.countPartners.text = "$countPartners предложений"
            }
            if (countPartners == 0) {
                binding.countPartners.text = "нет предложений"
            }
        }

        private fun setColor(color: String) {
            binding.cvPartner.setCardBackgroundColor(Color.parseColor(color))
        }

        private fun setImage(partnerImageUrl: String) {
            //Picasso.get().load(partnerImageUrl).into(binding.categoryImage)
            Glide.with(itemView.context).load(partnerImageUrl).into(binding.categoryImage)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val view = RcItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        val holder = MyHolder(view)
        view.root.setOnClickListener {
            itemClickListener(listItemCategory[holder.adapterPosition])

        }
        return holder

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(listItemCategory[position])
    }

    override fun getItemCount(): Int {
        return listItemCategory.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListCategory(list: List<PartnerCategoryDetail>) {
        if (list.isEmpty() || list != listItemCategory) {
            listItemCategory.clear()
            listItemCategory.addAll(list)
            notifyDataSetChanged()
        }

    }
}