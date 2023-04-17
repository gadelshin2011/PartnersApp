package com.example.partnersapp.presenter.network

import com.example.partnersapp.model.AuthRequest
import com.example.partnersapp.model.Detail
import com.example.partnersapp.model.authorization
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface InterfaceApi {
    @Headers(
        "withAuth: false",
        "AppKey:sCNXJoLWDSchbDRouriS"
    )
    @POST("/api/v0/token/")
    suspend fun auth(@Body authRequest: AuthRequest): Response<authorization>






}