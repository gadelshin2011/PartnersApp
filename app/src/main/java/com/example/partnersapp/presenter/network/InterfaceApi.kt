package com.example.partnersapp.presenter.network

import com.example.partnersapp.model.authModels.AuthRequest
import com.example.partnersapp.model.authModels.AuthData
import com.example.partnersapp.model.partnerModels.PartnersModel
import retrofit2.Response
import retrofit2.http.*

interface InterfaceApi {
    //TODO чтобы не добавлять для каждого запроса одинаковые данные, используют Interceptors
    //https://stackoverflow.com/questions/32196424/how-to-add-headers-to-okhttp-request-interceptor
    // Один из ответов по ссылочке выше
//    fun okHttpClientFactory(): OkHttpClient {
//        return OkHttpClient().newBuilder()
//            .addInterceptor { chain ->
//                chain.request().newBuilder()
//                    .addHeader(HEADER_AUTHONRIZATION, O_AUTH_AUTHENTICATION)
//                    .build()
//                    .let(chain::proceed)
//            }
//            .build()
//    }
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