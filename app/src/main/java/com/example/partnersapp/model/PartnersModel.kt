package com.example.partnersapp.model

data class PartnersModel(
    val detail: PartnersDetail,
    val error_message: Any,
    val status: String,
    val status_id: Int
)