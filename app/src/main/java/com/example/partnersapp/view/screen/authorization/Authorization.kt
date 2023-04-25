package com.example.partnersapp.view.screen.authorization

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.partnersapp.R
import com.example.partnersapp.databinding.FragmentAuthorizationBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.content.Intent
import android.net.Uri


class Authorization : Fragment() {
    lateinit var binding: FragmentAuthorizationBinding
    private val viewModel: AuthViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visibility = View.GONE
        clearDataFromEdText()
        init()
        setListener()
        isEnableBtn()

    }

    private fun isEnableBtn() {
        binding.edPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.tvError.text = ""
                binding.btnAuthorization.isEnabled = s?.length!! > 5
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        binding.edLogin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.tvError.text = ""
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setListener() {
        binding.buttonLogPass.setOnClickListener {
            binding.apply {
                edLogin.setText(getString(R.string.TextLogin)).toString()
                edPassword.setText(getString(R.string.TestPassword)).toString()
            }

            binding.imageButtonPassVisible.setOnClickListener {
                binding.imageButtonPassVisible.isSelected =
                    !binding.imageButtonPassVisible.isSelected

                if (!binding.imageButtonPassVisible.isSelected) {
                    binding.edPassword.transformationMethod =
                        PasswordTransformationMethod.getInstance();
                } else {
                    binding.edPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance();
                }
            }

            binding.tvUserAgr.setOnClickListener {
                val browser = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ufanet.ru/media/uploads/2021/11/30/Dizi.pdf"))
                startActivity(browser)
            }
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun init() {

        binding.btnAuthorization.setOnClickListener {

            onCloseKeyboard()

            binding.btnAuthorization.isEnabled = false

            val login = binding.edLogin.text.toString()
            val password = binding.edPassword.text.toString()

            if (checkingData(login, password)) {
                GlobalScope.launch {
                    val token = viewModel.requestToken(login, password)
                    //Log.d("MyLog", "token = $token")

                    GlobalScope.launch(Dispatchers.Main) {
                        if (token != null) {
                            findNavController().navigate(R.id.action_authorization_to_partnersScreen)
                        } else {
                            Toast.makeText(
                                context,
                                getString(R.string.ErrorAuthorization),
                                Toast.LENGTH_SHORT
                            ).show()
                            clearDataFromEdText()
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
            binding.tvError.text = getString(R.string.ErrorText)

            false
        } else {
            true
        }

    }

    private fun clearDataFromEdText() {
        binding.edLogin.setText("")
        binding.edPassword.setText("")
    }

}



