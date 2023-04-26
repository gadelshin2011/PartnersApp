package com.example.partnersapp.model.authModels

import com.google.gson.annotations.SerializedName

data class AuthData(
    val detail: Detail,
//    TODO для названия свойств ипользуют camel case (errorMessage)
//    Если с бэка приходят данные с полями отличающимися по формату или названию,
//    то модельки на клиенте не подстраиваются под бэк, а используют анатации от парсеров. В данном случае будет     @SerializedName("error_message")
    val error_message: String,
    val status: String,
    val status_id: Int
)

//Example
//data class AuthData(
//    val detail: Detail,
//    @SerializedName("error_message")
//    val errorMessage: String,
//    val status: String,
//    @SerializedName("status_id")
//    val statusId: Int
//)