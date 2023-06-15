package com.example.partnersapp.model.partnerModels.category

import com.google.gson.annotations.SerializedName

data class PartnerCategory(
    val detail: List<PartnerCategoryDetail>,
    @SerializedName("error_message")
    val errorMessage: String,
    val status: String,
    @SerializedName("status_id")
    val statusId: Int
)