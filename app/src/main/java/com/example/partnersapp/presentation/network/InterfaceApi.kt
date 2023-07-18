package com.example.partnersapp.presentation.network

import com.example.partnersapp.model.authModels.AuthData
import com.example.partnersapp.model.authModels.AuthRequest
import com.example.partnersapp.model.partnerModels.allPartners.PartnersModel
import com.example.partnersapp.model.partnerModels.category.PartnerCategory
import com.example.partnersapp.model.partnerModels.category.categoryNews.PartnerCategoryNew
import com.example.partnersapp.model.partnerModels.category.showCategory.ShowCategory
import retrofit2.Response
import retrofit2.http.*

interface InterfaceApi {

    @POST("/api/v0/token/")
    suspend fun auth(@Body authRequest: AuthRequest): Response<AuthData>

    @GET("/api/v0/partner/all_partners/?city_id=1&partner_id=1")
    suspend fun getPartners(
        @Query("page") page: Int,
        @Header("Authorization") tokenM: String
    ): Response<PartnersModel>

    @GET("/api/v0/partner/category/?city=1")
    suspend fun getPartnerCategory(
        @Header("Authorization") tokenM: String
    ): PartnerCategory

    @GET("/api/v0/new_partner/{city_id}/")
    suspend fun getNewPartners(
        @Path("city_id") id: Int,
        @Header("Authorization") tokenM: String
    ): Response<PartnerCategoryNew>

    @GET("/api/v0/partner/showcategory/{city_id}/")
    suspend fun getListPartnersInCategory(
        @Path("city_id") cityId: String,
        @Query("category") categoryId:Int,
        @Header("Authorization") tokenM: String,
    ): Response<ShowCategory>



}