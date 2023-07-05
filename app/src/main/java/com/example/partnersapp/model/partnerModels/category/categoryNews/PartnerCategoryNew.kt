package com.example.partnersapp.model.partnerModels.category.categoryNews

import com.google.gson.annotations.SerializedName

data class PartnerCategoryNew(
    val detail: List<PartnerCategoryNewDetail>,
    @SerializedName("error_message")
    val errorMessage: String,
    val status: String,
    @SerializedName("status_id")
    val statusId: Int
)