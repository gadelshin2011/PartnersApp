package com.example.partnersapp.model.partnerModels

import com.google.gson.annotations.SerializedName

data class Partner(
    val color: String,
    val discount: Int,
    val id: Int,
    val id_category: Int,
    @SerializedName("name")
    val partnerName: String,
    @SerializedName("partner_image_url")
    val partner_image: String,
    @SerializedName("partner_logo_url")
    val partner_logo: String,
    val position: Int,
    val promotion_description: String,
    val sphere_name: String,
    val text_discount: String
)
