package com.example.partnersapp.view.screen.authorization.partners

import androidx.lifecycle.ViewModel
import com.example.partnersapp.presenter.network.WebRepository

class PartnersViewM : ViewModel() {
    private val webRepo = WebRepository()
    private var token: String = ""


//    private val _partners: MutableStateFlow<List<AuthData>> = MutableStateFlow(
//        emptyList()
//
//    )
//    val partners = _partners.asStateFlow()
//
//    init {
//        requestToken()
//    }
//
//    private fun requestToken() {
//
//        viewModelScope.launch {
//            val result = webRepo.retrofit.getPartners(token)
////            Log.d("MyLog", "$response")
//
//        }
//    }


}