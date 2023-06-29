package com.example.partnersapp.model.partnerModels.allPartners

import com.example.partnersapp.model.partnerModels.allPartners.Partner

data class PartnersDetail(
    val page_number: Int,
    val partners: List<Partner>,
    val total_pages: Int
)
