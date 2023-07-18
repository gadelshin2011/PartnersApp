package com.example.partnersapp.model.partnerModels.category.showCategory

data class ShowCategory(
    val detail: List<DetailPartner>,
    val error_message: String,
    val status: String,
    val status_id: Int
)