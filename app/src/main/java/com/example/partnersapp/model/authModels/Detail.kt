package com.example.partnersapp.model.authModels

import com.google.gson.annotations.SerializedName

data class Detail(
    @SerializedName("access")
    val accessToken: String,
    val expires_in: Double,
    @SerializedName("refresh")
    val refreshToken: String
)
