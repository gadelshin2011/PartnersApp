package com.example.partnersapp.view.screen.authorization

import android.util.Log
import android.util.MutableFloat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.partnersapp.model.AuthData
import com.example.partnersapp.model.AuthRequest
import com.example.partnersapp.model.Partner
import com.example.partnersapp.model.PartnersModel
import com.example.partnersapp.presenter.network.WebRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

abstract class AuthViewModel : ViewModel() {

    private val webRepo = WebRepository()
    var tokenM: String = ""
    var isSuc: Boolean = false



    private val _partners: MutableStateFlow<List<Partner>> = MutableStateFlow(
        emptyList()

    )
    val partners = _partners.asStateFlow()



    init {


    }


    fun requestToken(authRequest: AuthRequest) {
        viewModelScope.launch {
            val response = webRepo.retrofit.auth(authRequest)
            Log.d("MyLog", "$response")

            tokenM = response.body()?.detail?.access.toString()
                Log.d("MyLog", tokenM)





        }
    }

    fun requestPartners() {

        viewModelScope.launch {
            val result = webRepo.retrofit.getPartners("JWT $tokenM")
            Log.d("MyLog", "$result")
            _partners.value = result.detail.partners
        }
    }

}
