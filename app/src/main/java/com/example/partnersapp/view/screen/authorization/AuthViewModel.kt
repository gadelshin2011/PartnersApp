package com.example.partnersapp.view.screen.authorization

import android.util.JsonToken
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partnersapp.model.authModels.AuthRequest
import com.example.partnersapp.model.partnerModels.Partner
import com.example.partnersapp.presenter.network.WebRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class AuthViewModel : ViewModel() {

    private val webRepo = WebRepository()
    var tokenM: String = ""


//    private val _authDataM: MutableStateFlow<List<AuthData>> = MutableStateFlow(
//        emptyList()
//
//    )
//    val authorizationM = _authDataM.asStateFlow()

    private val _partners: MutableStateFlow<List<Partner>> = MutableStateFlow(
        emptyList()

    )
    val partners = _partners.asStateFlow()

    init {
        //requestPartners()
    }


    suspend fun requestToken(login: String, password: String): String? {

        val response = webRepo.retrofit.auth(AuthRequest(login, password))
        Log.d("MyLog", "$response")

        tokenM = response.body()?.detail?.access.toString()
        Log.d("MyLog", tokenM)

        return if (response.isSuccessful && response.body()?.status_id == 200) {
            response.body()?.detail?.access
        } else {
            response.body()?.error_message
        }
    }

    fun requestPartners() {

        viewModelScope.launch {
            val result = webRepo.retrofit.getPartners("JWT $tokenM")
            Log.d("MyLog", "$result")
            _partners.value = result.detail.partners

            Log.d("MyLog", _partners.value.toString())


        }
    }

}
