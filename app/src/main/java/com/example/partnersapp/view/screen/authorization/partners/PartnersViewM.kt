package com.example.partnersapp.view.screen.authorization.partners

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.partnersapp.model.partnerModels.allPartners.Partner
import com.example.partnersapp.presentation.db.DataStoreManager
import com.example.partnersapp.presentation.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PartnersViewM(apl: Application) : AndroidViewModel(apl) {

    private val webRepo = RetrofitClient()
    private val dateS = DataStoreManager(getApplication())

    private val _partners: MutableStateFlow<List<Partner>> = MutableStateFlow(
        emptyList()
    )
    val partners = _partners.asStateFlow()

    init {}

    fun requestPartners() {
        viewModelScope.launch {
            val tokenM = dateS.loadToken()
            val result = webRepo.retrofit.getPartners("JWT $tokenM")
//            Log.d("MyLog", "$tokenM")
            _partners.value = result.detail.partners
        }
    }


}