package com.example.partnersapp.model.partnerModels

import com.google.gson.annotations.SerializedName

data class PartnersModel(
    val detail: PartnersDetail,
    @SerializedName("error_message")
    val errorMessage: String,
    val status: String,
    @SerializedName("status_id")
    val statusId: Int
)