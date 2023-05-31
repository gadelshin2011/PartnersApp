package com.example.partnersapp.view.screen.authorization

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partnersapp.model.authModels.AuthRequest
import com.example.partnersapp.model.partnerModels.Partner
import com.example.partnersapp.presentation.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val webRepo = RetrofitClient()
    var tokenM: String = ""


    private val _partners: MutableStateFlow<List<Partner>> = MutableStateFlow(
        emptyList()

    )
    val partners = _partners.asStateFlow()

    init {

    }


    suspend fun requestToken(login: String, password: String): String? {

        val response = webRepo.retrofit.auth(AuthRequest(login, password))
        Log.d("MyLog", "$response")

        tokenM = response.body()?.detail?.accessToken.toString()
        Log.d("MyLog", tokenM)

        return if (response.isSuccessful && response.body()?.statusId == 200) {
            response.body()?.detail?.accessToken
        } else {
            null
        }
    }

    fun requestPartners() {

        viewModelScope.launch {
            val result = webRepo.retrofit.getPartners("JWT $tokenM")
//            Log.d("MyLog", "$result")
            _partners.value = result.detail.partners



        }
    }

}
