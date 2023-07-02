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


class PartnersViewM(apl: Application) : AndroidViewModel(apl) {

    private val webRepo = RetrofitClient()
    private val dateS = DataStoreManager(getApplication())
    private var page = 1

    private val _partners: MutableStateFlow<List<Partner>> = MutableStateFlow(
        emptyList()
    )
    val partners = _partners.asStateFlow()

    private val _partnersCategory: MutableStateFlow<List<PartnerCategoryDetail>> = MutableStateFlow(
        emptyList()
    )
    val partnersCategory = _partnersCategory.asStateFlow()


    init { }

    suspend fun requestPartners() {
            val tokenM = dateS.loadToken()
            val result = webRepo.retrofit.getPartners(page,"JWT $tokenM")
                if (result.isSuccessful && result.body()?.statusId == 200){
                    page += 1
                    _partners.value = result.body()!!.detail.partners
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