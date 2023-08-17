package com.example.partnersapp.view.listPartnersFromCategory.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.partnersapp.model.partnerModels.category.showCategory.DetailPartner
import com.example.partnersapp.presentation.db.DataStoreManager
import com.example.partnersapp.presentation.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PartnerFromCategoryViewModel(apl: Application) : AndroidViewModel(apl) {
    private val webRepo = RetrofitClient()
    private val dateS = DataStoreManager(getApplication())
    private var cityId = "1"

    private val _partnersFromCategory: MutableStateFlow<List<DetailPartner>> = MutableStateFlow(
        emptyList()
    )
    val partnersFromCategory = _partnersFromCategory.asStateFlow()

    suspend fun requestPartners(categoryId:Int): String {
        val tokenM = dateS.loadToken()
        val result = webRepo.retrofit.getListPartnersInCategory(cityId,categoryId,"JWT $tokenM")
        return if (result.isSuccessful) {
            _partnersFromCategory.value = result.body()!!.detail
            "Ok"
        } else {
            "Finished loading data"
        }
    }
}