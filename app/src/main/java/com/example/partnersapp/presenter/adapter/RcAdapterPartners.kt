package com.example.partnersapp.presenter.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.partnersapp.R
import com.example.partnersapp.databinding.RcItemBinding
import com.example.partnersapp.model.partnerModels.Partner
import com.squareup.picasso.Picasso

//TODO Название пакета не соответствует его работе/назвачение/типу. Presenter - обычно воспринимается как часть из паттерна MVP (ИМХО)
// Лучше не сокращать слово presentation (если ты это сделал)
class RcAdapterPartners : RecyclerView.Adapter<RcAdapterPartners.MyHolder>() {
    // TODO префикс "Rc" можно опускать, не особо встречал такой стиль, да и как-то без него понятно (ИМХО)

    //TODO Используется только внутри этого класса - нужно сделать private
    var listItem: MutableList<Partner> = mutableListOf()

    class MyHolder(private val binding: RcItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(part: Partner) {
            setName(part.name)
            setSphereName(part.sphere_name)
            setDiscount(part.text_discount)
            setPromotionDescription(part.promotion_description)
            setImage(part.partner_image_url)
            setLogo(part.partner_logo_url)
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
        listItem.addAll(list)
        notifyDataSetChanged()
    }
}