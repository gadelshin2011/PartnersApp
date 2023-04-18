package com.example.partnersapp.view.screen.authorization

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partnersapp.model.AuthRequest
import com.example.partnersapp.model.authData
import com.example.partnersapp.presenter.network.WebRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class AuthViewModel: ViewModel() {

     private val webRepo = WebRepository()
    var tokenM: String =""
    var statusM: String =""
    var errorMess: String =""


    private val _authDataM: MutableStateFlow<List<authData>> = MutableStateFlow(
        emptyList()

    )
    val authorizationM = _authDataM.asStateFlow()

    init {
         tokenM
         statusM
         errorMess
    }


     fun requestToken(authRequest: AuthRequest) {
        viewModelScope.launch {
            val response = webRepo.retrofit.auth(authRequest)
            Log.d("MyLog","$response")


            val token = response.body()?.detail?.access
            Log.d("MyLog","$token")

            tokenM = token.toString()

            val status = response.body()?.status
            Log.d("MyLog","$status")

            statusM = status.toString()

            val errorMessage = response.body()?.error_message
            errorMess = errorMessage.toString()
            Log.d("MyLog","$errorMess")











        }
    }
}
