package com.example.partnersapp.view.screen.authorization

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.partnersapp.R
import com.example.partnersapp.databinding.FragmentAuthorizationBinding
import com.example.partnersapp.presenter.network.WebRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


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
        binding.progressBar.visibility = View.GONE
        binding.edLogin.setText("")
        binding.edPassword.setText("")
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
                edLogin.setText("72855249").toString()
                edPassword.setText("8783446692").toString()
            }

        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun init() {

        binding.btnAuthorization.setOnClickListener {
            val login = binding.edLogin.text.toString()
            val password = binding.edPassword.text.toString()

            GlobalScope.launch {
                val token = viewModel.requestToken(login, password)

                Log.d("MyLog", "token = $token")

                GlobalScope.launch(Dispatchers.Main) {
                    if (token != null) {
                        findNavController().navigate(R.id.action_authorization_to_partnersScreen)
                    } else {
                        Toast.makeText(context, token, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}



