package com.example.partnersapp.presenter.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.partnersapp.databinding.ItemPartnerBinding
import com.example.partnersapp.databinding.RcItemBinding
import com.example.partnersapp.model.Partner
import com.squareup.picasso.Picasso

class RcAdapterPartners : RecyclerView.Adapter<RcAdapterPartners.MyHolder>() {

    var listItem: MutableList<Partner> = mutableListOf()

    class MyHolder(private val binding: RcItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(part: Partner) {
            setName(part.name)
            setSphereName(part.sphere_name)
            setDiscount(part.text_discount)
            setPromotionDescription(part.promotion_description)
            setImage(part.partner_image_url)
            setLogo(part.partner_logo_url)

        }

        private fun setLogo(partnerLogoUrl: String) {
            Picasso.get().load(partnerLogoUrl).into(binding.ivLogoPartner)
        }

        private fun setImage(partnerImageUrl: String) {
            Picasso.get().load(partnerImageUrl).into(binding.partnerImage)
        }

        private fun setPromotionDescription(promotionDescription: String) {
            binding.promotionDescription.text = promotionDescription
        }

        private fun setDiscount(textDiscount: String) {
            binding.textDiscount.text = textDiscount
        }

        private fun setSphereName(sphereName: String) {
            binding.sphereName.text = sphereName
        }

        private fun setName(name: String) {
            binding.partnerName.text = name
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            RcItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
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
    fun setList(list: List<Partner>) {
        listItem.addAll(list)
        notifyDataSetChanged()
    }
}