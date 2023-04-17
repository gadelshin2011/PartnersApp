package com.example.partnersapp.view.screen.authorization

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partnersapp.model.AuthRequest
import com.example.partnersapp.presenter.network.WebRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class AuthViewModel: ViewModel() {

     private val webRepo = WebRepository()



//
//    private val _auth: MutableStateFlow<List<Authorization>> = MutableStateFlow(
//        emptyList()
//    )
//    val auth = _auth.asStateFlow()
//
//    init {
//        requestToken()
//    }

     fun requestToken(authRequest: AuthRequest) {
        viewModelScope.launch {
            val response = webRepo.retrofit.auth(authRequest)
            Log.d("MyLog","$response")
//            val token = response.body()?.detail?.access
//            Log.d("MyLog","$token")



        }
    }
}
