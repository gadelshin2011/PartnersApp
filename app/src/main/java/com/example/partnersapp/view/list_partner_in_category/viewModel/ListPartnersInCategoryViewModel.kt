package com.example.partnersapp.view.list_partner_in_category.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.partnersapp.model.partnerModels.category.showCategory.DetailPartner
import com.example.partnersapp.presentation.db.DataStoreManager
import com.example.partnersapp.presentation.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListPartnersInCategoryViewModel(apl: Application) : AndroidViewModel(apl) {
    private val webRepo = RetrofitClient()
    private val dateS = DataStoreManager(getApplication())
    private var cityId = "1"

    private val _listPartners: MutableStateFlow<List<DetailPartner>> = MutableStateFlow(
        emptyList()
    )
    val listPartners = _listPartners.asStateFlow()

    init {

    }

    suspend fun requestPartnersInCategory(categoryID: Int): String {
        val tokenM = dateS.loadToken()
        val result = webRepo.retrofit.getListPartnersInCategory(cityId, categoryID, "JWT $tokenM")
        return if (result.isSuccessful) {
            _listPartners.value = result.body()!!.detail
            "Ok"
        } else {
            "Potracheno"
        }
    }


}