package com.example.partnersapp.model.partnerModels.category.categoryNews

import com.google.gson.annotations.SerializedName

data class PartnerCategoryNewDetail(
    val color: String,
    val discount: Int,
    val id: Int,
    @SerializedName("id_partner")
    val partnerId: Int,
    val name_category: String,
    val name_partner: String,
    @SerializedName("partner_image_url")
    val partnerImage: String,
    @SerializedName("partner_logo_url")
    val partnerLogo: String,
    val position: Int,
    @SerializedName("promotion_description")
    val promotionDescription: String,
    @SerializedName("sphere_name")
    val sphereName: String,
    @SerializedName("text_discount")
    val textDiscount: String
)
