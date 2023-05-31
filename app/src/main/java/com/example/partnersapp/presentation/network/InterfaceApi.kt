package com.example.partnersapp.presentation.network

import com.example.partnersapp.model.authModels.AuthRequest
import com.example.partnersapp.model.authModels.AuthData
import com.example.partnersapp.model.partnerModels.PartnersModel
import retrofit2.Response
import retrofit2.http.*

interface InterfaceApi {

    @POST("/api/v0/token/")
    suspend fun auth(@Body authRequest: AuthRequest): Response<AuthData>

    @GET("/api/v0/partner/all_partners/?city_id=1&page=1&partner_id=1")
    suspend fun getPartners(@Header("Authorization") tokenM:String) : PartnersModel






}