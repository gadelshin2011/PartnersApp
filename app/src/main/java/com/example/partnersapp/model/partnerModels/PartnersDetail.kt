package com.example.partnersapp.model.partnerModels

data class PartnersDetail(
    val page_number: Int,
    val partners: List<Partner>,
    val total_pages: Int
)
