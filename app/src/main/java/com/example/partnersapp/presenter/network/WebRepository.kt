package com.example.partnersapp.presenter.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//TODO Так как для сетевых запросов в нужен только один экземпляр retrofit и okhttp (конкретно в данном случае достаточно одного),
// так как создавать несколько экземпляров нерациональное использование ресурсов системы.
// Поэтому можно реализовать синглтон - в котлине для этого служит object
// Также найминг этого класса чуток не подходит под его функционал - репозиторий отвечает передачу данных от источников (сетевых, локальных)
class WebRepository {

  private var client: OkHttpClient

    init {
      val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client = OkHttpClient.Builder().addInterceptor(interceptor).build()
  }

    var retrofit: InterfaceApi = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(client).baseUrl(
        BASE_URL).build().create(InterfaceApi::class.java)

  //TODO если константа не использется в других классах, то можно ее сразу делать private
  // Также можно сделать приватным companion object, если его методы и свойства используются только внутри класса
    companion object {
        const val BASE_URL = "https://api.ufanet.ru"
    }

}