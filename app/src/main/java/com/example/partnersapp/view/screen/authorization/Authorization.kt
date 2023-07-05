package com.example.partnersapp.view.screen.authorization

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.partnersapp.R
import com.example.partnersapp.databinding.FragmentAuthorizationBinding
import com.example.partnersapp.view.screen.authorization.viewModel.AuthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Authorization : Fragment() {
    lateinit var binding: FragmentAuthorizationBinding
    private val viewModel: AuthViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clearDataFromEdText()
        init()
        setListener()
        isEnableBtn()
    }

    private fun isEnableBtn() {
        binding.edPassword.doOnTextChanged { text, _, _, _ ->
            binding.tvError.text = ""
            binding.btnAuthorization.isEnabled = text?.length!! > 5
        }

        binding.edLogin.doOnTextChanged { _, _, _, _ ->
            binding.tvError.text = ""
        }
    }

    private fun setListener() {
        binding.imageButtonPassVisible.setOnClickListener {
            binding.imageButtonPassVisible.isSelected =
                !binding.imageButtonPassVisible.isSelected

            if (!binding.imageButtonPassVisible.isSelected) {
                binding.edPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            } else {
                binding.edPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }
        }

        binding.greetingField.setOnClickListener {

            binding.apply {
                edLogin.setText(getString(R.string.text_login)).toString()
                edPassword.setText(getString(R.string.test_password)).toString()
            }
        }

        binding.tvUserAgr.setOnClickListener {
            val browser = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.ufanet.ru/media/uploads/2021/11/30/Dizi.pdf")
            )
            startActivity(browser)
        }
    }

    private fun init() {

        binding.btnAuthorization.setOnClickListener {
            onCloseKeyboard()
            binding.progressCard.visibility = View.VISIBLE
            binding.btnAuthorization.isEnabled = false
            binding.edLogin.isEnabled = false
            binding.edPassword.isEnabled = false

            val login = binding.edLogin.text.toString()
            val password = binding.edPassword.text.toString()

            if (checkingData(login, password)) {
                lifecycleScope.launch(Dispatchers.IO) {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        val token = viewModel.requestToken(login, password)

                        lifecycleScope.launch {
                            if (token != null) {
                                binding.progressCard.visibility = View.GONE
                                findNavController().navigate(R.id.action_authorization_to_partnersScreen)
                            } else {
                                actionWithElement()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onCloseKeyboard() {
        val inputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.edPassword.windowToken, 0)
        inputMethodManager.hideSoftInputFromWindow(binding.edLogin.windowToken, 0)
        binding.edLogin.clearFocus()
        binding.edPassword.clearFocus()
    }

    private fun checkingData(login: String, password: String): Boolean {
        return if (login.length < 8 || password.length < 8) {
            actionWithElement()
            false
        } else {
            true
        }
    }

    private fun clearDataFromEdText() {
        binding.edLogin.setText("")
        binding.edPassword.setText("")
    }

    private fun actionWithElement() {
        binding.tvError.text = getString(R.string.error_text)
        binding.edLogin.isEnabled = true
        binding.edPassword.isEnabled = true
        binding.btnAuthorization.isEnabled = true
        binding.progressCard.visibility = View.GONE

    }

}



