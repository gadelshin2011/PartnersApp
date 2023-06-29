package com.example.partnersapp.view.screen.authorization.partners

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.partnersapp.model.partnerModels.allPartners.Partner
import com.example.partnersapp.model.partnerModels.category.PartnerCategoryDetail
import com.example.partnersapp.presentation.db.DataStoreManager
import com.example.partnersapp.presentation.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class PartnersViewM(apl: Application) : AndroidViewModel(apl) {

    private val webRepo = RetrofitClient()
    private val dateS = DataStoreManager(getApplication())

    private val _partners: MutableStateFlow<List<Partner>> = MutableStateFlow(
        emptyList()
    )
    val partners = _partners.asStateFlow()

    private val _partnersCategory: MutableStateFlow<List<PartnerCategoryDetail>> = MutableStateFlow(
        emptyList()
    )
    val partnersCategory = _partnersCategory.asStateFlow()


    init { }

    fun requestPartners() {
        viewModelScope.launch(Dispatchers.IO) {
            val tokenM = dateS.loadToken()
            val result = webRepo.retrofit.getPartners("JWT $tokenM")
//            Log.d("MyLog", "$tokenM")
            _partners.value = result.detail.partners
        }
    }
    fun requestPartnerCategory(){
        viewModelScope.launch(Dispatchers.IO) {
            val tokenM = dateS.loadToken()
            val resultCategory = webRepo.retrofit.getPartnerCategory("JWT $tokenM")
            _partnersCategory.value = resultCategory.detail
        }
    }


}