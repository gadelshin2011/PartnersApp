package com.example.partnersapp.model

data class Partner(
    val color: String,
    val discount: Int,
    val id: Int,
    val id_category: Int,
    val name: String,
    val partner_image_url: String,
    val partner_logo_url: String,
    val position: Int,
    val promotion_description: String,
    val sphere_name: String,
    val text_discount: String
)
