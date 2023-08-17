package com.example.partnersapp.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.partnersapp.databinding.RcItemBinding
import com.example.partnersapp.model.partnerModels.allPartners.Partner
import com.example.partnersapp.model.partnerModels.category.showCategory.DetailPartner
import com.example.partnersapp.model.partnerModels.category.showCategory.ShowCategory
import com.squareup.picasso.Picasso

class AdapterPartners : RecyclerView.Adapter<AdapterPartners.MyHolder>() {

    private var listItem: MutableList<Partner> = mutableListOf()


    class MyHolder(private val binding: RcItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(part: Partner) {
            setName(part.partnerName)
            setSphereName(part.sphere_name)
            setDiscount(part.text_discount)
            setPromotionDescription(part.promotion_description)
            setImage(part.partner_image)
            setLogo(part.partner_logo)
            setColor(part.color)

        }

        private fun setColor(color: String) {
            binding.cvPartner.setCardBackgroundColor(Color.parseColor(color))
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
        if (listItem != list || listItem.isEmpty())
            listItem.addAll(list)
        notifyDataSetChanged()
    }

//    private class PersonDiffUtil(
//        val newList: List<Partner>,
//        val oldList: List<Partner>
//    ) : DiffUtil.Callback() {
//        override fun getOldListSize(): Int {
//            return oldList.size
//        }
//
//        override fun getNewListSize(): Int {
//            return newList.size
//        }
//
//        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//            return newList[newItemPosition].id == oldList[oldItemPosition].id
//        }
//
//        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//            return newList[newItemPosition] == oldList[oldItemPosition]
//        }
//
//    }

}