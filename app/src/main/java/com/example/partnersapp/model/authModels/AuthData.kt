package com.example.partnersapp.model.authModels

data class AuthData(
    val detail: Detail,
    val error_message: String,
    val status: String,
    val status_id: Int
)