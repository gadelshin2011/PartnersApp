package com.example.partnersapp.presentation.adapter


import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.partnersapp.databinding.RcItemBinding
import com.example.partnersapp.model.partnerModels.category.showCategory.DetailPartner
import com.squareup.picasso.Picasso

class ShowCategoryAdapter : RecyclerView.Adapter<ShowCategoryAdapter.MyHolder>() {
    private var listItem: MutableList<DetailPartner> = mutableListOf()


    class MyHolder(private val binding: RcItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(part: DetailPartner) {
            setName(part.namePartner)
            setSphereName(part.sphereName)
            setDiscount(part.textDiscount)
            setPromotionDescription(part.promotionDescription)
            setImage(part.partnerImage)
            setLogo(part.partnerLogo)
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

    fun setList(list: List<DetailPartner>) {
        val personDiffUtil = PersonDiffUtil(
            oldList = listItem,
            newList = list
        )
        val diffResult = DiffUtil.calculateDiff(personDiffUtil)
        listItem.clear()
        listItem.addAll(list)
        diffResult.dispatchUpdatesTo(this)

    }

    private  class PersonDiffUtil(
        val newList: List<DetailPartner>,
        val oldList: List<DetailPartner>
    ): DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return  oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return newList[newItemPosition].id == oldList[oldItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return newList[newItemPosition] == oldList[oldItemPosition]
        }

    }



}