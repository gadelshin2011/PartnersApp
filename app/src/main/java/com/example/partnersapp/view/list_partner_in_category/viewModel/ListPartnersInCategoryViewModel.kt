package com.example.partnersapp.view.list_partner_in_category.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.partnersapp.model.partnerModels.allPartners.Partner
import com.example.partnersapp.presentation.db.DataStoreManager
import com.example.partnersapp.presentation.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListPartnersInCategoryViewModel(apl: Application) : AndroidViewModel(apl) {
    private val webRepo = RetrofitClient()
    private val dateS = DataStoreManager(getApplication())
    private var cityId = "1"

    private val _listPartners: MutableStateFlow<List<Partner>> = MutableStateFlow(
        emptyList()
    )
    val listPartners = _listPartners.asStateFlow()

    init {}

    suspend fun requestPartnersInCategory(category_id: Int): String? {
        val tokenM = dateS.loadToken()

        val result = webRepo.retrofit.getListPartnersInCategory(cityId, category_id, "JWT $tokenM")
        return if (result.isSuccessful) {
            _listPartners.value = result.body()!!.detail.partners
            "Ok"
        } else {
            "Potracheno"
        }
    }


}