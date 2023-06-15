package com.example.partnersapp.model.partnerModels.category

import com.google.gson.annotations.SerializedName

data class PartnerCategoryDetail(
    val color: String,
    @SerializedName("count_partners")
    val countPartners: Int,
    val id: Int,
    @SerializedName("image_color")
    val imageColor: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val position: Int,
    val title: String
)
