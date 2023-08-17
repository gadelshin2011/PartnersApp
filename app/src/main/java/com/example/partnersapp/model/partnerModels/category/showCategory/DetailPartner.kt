package com.example.partnersapp.model.partnerModels.category.showCategory

import com.google.gson.annotations.SerializedName

data class DetailPartner(
    val color: String,
    val discount: Int,
    val id: Int,
    @SerializedName("id_partner")
    val idPartner: Int,
    @SerializedName("is_ufanet")
    val isUfanet: Boolean,
    @SerializedName("name_partner")
    val namePartner: String,
    @SerializedName("partner_active_before")
    val partnerActiveBefore: Int,
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
