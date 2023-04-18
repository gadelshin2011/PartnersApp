package com.example.partnersapp.presenter.network

import com.example.partnersapp.model.AuthRequest
import com.example.partnersapp.model.AuthData
import com.example.partnersapp.model.PartnersModel
import retrofit2.Response
import retrofit2.http.*

interface InterfaceApi {
    @Headers(
        "withAuth: false",
        "AppKey:sCNXJoLWDSchbDRouriS"
    )
    @POST("/api/v0/token/")
    suspend fun auth(@Body authRequest: AuthRequest): Response<AuthData>


    @Headers(
        "withAuth: false",
        "AppKey:sCNXJoLWDSchbDRouriS"
    )
    @GET("/api/v0/partner/all_partners/?city_id=1&page=1&partner_id=1")
    suspend fun getPartners(@Header("Authorization") tokenM:String) : PartnersModel






}