package com.example.partnersapp.model.partnerModels.category

import android.os.Parcelable
import com.example.partnersapp.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryNew(
    val categoryName: String = "Новинки",
    val categoryImage: Int = R.drawable.category_new,
    val categoryCount: Int = 0,
    val id_category: Int = 999


): Parcelable
