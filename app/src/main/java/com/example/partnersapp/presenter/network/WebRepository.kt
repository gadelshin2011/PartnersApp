package com.example.partnersapp.presenter.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WebRepository {

  private var client: OkHttpClient

    init {
      val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client = OkHttpClient.Builder().addInterceptor(interceptor).build()
  }

    var retrofit: InterfaceApi = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(client).baseUrl(
        BASE_URL).build().create(InterfaceApi::class.java)

    companion object {
        const val BASE_URL = "https://api.ufanet.ru"
    }

}