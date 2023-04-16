package com.example.partnersapp.model

data class authorization(
    val detail: Detail,
    val error_message: Any,
    val status: String,
    val status_id: Int
)