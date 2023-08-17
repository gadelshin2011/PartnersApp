package com.example.partnersapp.view.partnersScreen.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.partnersapp.model.partnerModels.allPartners.Partner
import com.example.partnersapp.model.partnerModels.category.PartnerCategoryDetail
import com.example.partnersapp.model.partnerModels.category.categoryNews.PartnerCategoryNewDetail
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
    private var cityId = 1

    private val _partners: MutableStateFlow<List<Partner>> = MutableStateFlow(
        emptyList()
    )
    val partners = _partners.asStateFlow()

    private val _partnersCategory: MutableStateFlow<List<PartnerCategoryDetail>> = MutableStateFlow(
        emptyList()
    )
    val partnersCategory = _partnersCategory.asStateFlow()

    private val _newPartners: MutableStateFlow<List<PartnerCategoryNewDetail>> = MutableStateFlow(
        emptyList()
    )
    val newPartners = _newPartners.asStateFlow()


    init {}

    suspend fun requestPartners(): String {
        val tokenM = dateS.loadToken()

        val result = webRepo.retrofit.getPartners(page, "JWT $tokenM")
        return if (result.isSuccessful) {
            page += 1
            _partners.value = result.body()!!.detail.partners
            "Ok"
        } else {
            "Finished loading data"
        }
    }

    fun requestPartnerCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            val tokenM = dateS.loadToken()
            val resultCategory = webRepo.retrofit.getPartnerCategory("JWT $tokenM")
            _partnersCategory.value = resultCategory.detail
        }
    }

    suspend fun requestNewPartners(): String {
        val tokenM = dateS.loadToken()

        val result = webRepo.retrofit.getNewPartners(cityId, "JWT $tokenM")
        Log.d("MyLog", "$result")

        val listSize = result.body()!!.detail.size
        Log.d("MyLog", "$listSize")



        return if (result.isSuccessful) {
            _newPartners.value = result.body()!!.detail
            "Ok"
        } else {
            "loading category 'New' failed"
        }
    }


}