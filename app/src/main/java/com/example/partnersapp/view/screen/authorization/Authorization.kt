package com.example.partnersapp.view.screen.authorization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.partnersapp.R
import com.example.partnersapp.databinding.FragmentAuthorizationBinding
import com.example.partnersapp.model.AuthRequest
import com.example.partnersapp.presenter.network.WebRepository

class Authorization : Fragment() {
    lateinit var binding: FragmentAuthorizationBinding
    var webRepo= WebRepository()
    private val viewModel: AuthViewModel by activityViewModels()
    private val appKey = "sCNXJoLWDSchbDRouriS"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorizationBinding.inflate(inflater,container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setListener()
    }

    private fun setListener() {
        binding.buttonLogPass.setOnClickListener {
            binding.apply {
                edLogin.setText("72826119").toString()
                edPassword.setText("0123456789").toString()
            }

        }

    }

    private fun init() {
        binding.btnAuthorization.setOnClickListener {
            viewModel.requestToken(AuthRequest(
                login = binding.edLogin.text.toString(),
                password = binding.edPassword.text.toString(),
            ))
        }

    }


}