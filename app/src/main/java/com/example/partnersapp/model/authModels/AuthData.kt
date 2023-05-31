package com.example.partnersapp.model.authModels

import com.google.gson.annotations.SerializedName

data class AuthData(
    val detail: Detail,
    @SerializedName("error_message")
    val errorMessage: String,
    val status: String,
    @SerializedName("status_id")
    val statusId: Int
)