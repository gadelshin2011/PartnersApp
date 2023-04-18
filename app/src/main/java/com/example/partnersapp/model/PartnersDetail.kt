package com.example.partnersapp.model

data class PartnersDetail(
    val page_number: Int,
    val partners: List<Partner>,
    val total_pages: Int
)
