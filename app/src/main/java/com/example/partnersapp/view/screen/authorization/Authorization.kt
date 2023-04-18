package com.example.partnersapp.view.screen.authorization

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.partnersapp.R
import com.example.partnersapp.databinding.FragmentAuthorizationBinding
import com.example.partnersapp.model.AuthRequest
import com.example.partnersapp.presenter.network.WebRepository


class Authorization : Fragment() {
    lateinit var binding: FragmentAuthorizationBinding
    var webRepo = WebRepository()
    private val viewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setListener()
        isEnableBtn()

    }

    private fun isEnableBtn() {
        binding.edPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.tvError.text= ""
                binding.btnAuthorization.isEnabled = s?.length!! > 5
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        binding.edLogin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.tvError.text= ""
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setListener() {
        binding.buttonLogPass.setOnClickListener {
            binding.apply {
                edLogin.setText("72855249").toString()
                edPassword.setText("8783446692").toString()
            }

        }

    }

    private fun init() {
        binding.btnAuthorization.setOnClickListener {
//
//            if (binding.edLogin.text.isEmpty() || binding.edPassword.text.isEmpty()) {
//                //binding.tvError.visibility = View.VISIBLE
//                binding.tvError.text = "Неверный логин или пароль"
//            }
//            if (binding.edLogin.text.isNotEmpty() && binding.edPassword.text.isNotEmpty()) {
//                //binding.tvError.visibility = View.GONE
//                binding.tvError.text = ""
                viewModel.requestToken(
                    AuthRequest(
                        login = binding.edLogin.text.toString(),
                        password = binding.edPassword.text.toString()
                    )
                )
                findNavController().navigate(R.id.action_authorization_to_partnersScreen)
            }


        }

    }

//    private fun checkedStatusResponse(){
//        if (viewModel == "ok") {
//            //  findNavController().navigate(R.id.action_authorization_to_partnersScreen)
//            Toast.makeText(context, "Very good", Toast.LENGTH_SHORT).show()
//        }
//        if (auth.status == "error") {
//            Toast.makeText(context, auth.error_message.toString(), Toast.LENGTH_SHORT).show()
//
//        }
//
//
//
//    }
//
//
//}