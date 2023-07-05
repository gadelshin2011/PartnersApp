package com.example.partnersapp.view.screen.authorization.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.partnersapp.model.authModels.AuthRequest
import com.example.partnersapp.presentation.db.DataStoreManager
import com.example.partnersapp.presentation.network.RetrofitClient


class AuthViewModel(apl: Application) : AndroidViewModel(apl) {

    private val webRepo = RetrofitClient()
    private val dateS = DataStoreManager(getApplication())

    init {}

    suspend fun requestToken(login: String, password: String): String? {

        val response = webRepo.retrofit.auth(AuthRequest(login, password))
//        Log.d("MyLog", "$response")
        val tokenM = response.body()?.detail?.accessToken.toString()
        dateS.saveToken(tokenM)

//        Log.d("MyLog", tokenM)

        return if (response.isSuccessful && response.body()?.statusId == 200) {
            response.body()?.detail?.accessToken
        } else {
            null
        }
    }

}
