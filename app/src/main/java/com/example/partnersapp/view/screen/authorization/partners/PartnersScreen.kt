package com.example.partnersapp.view.screen.authorization.partners

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.partnersapp.R
import com.example.partnersapp.databinding.FragmentAuthorizationBinding
import com.example.partnersapp.databinding.FragmentPartnersScreenBinding

class PartnersScreen : Fragment() {
    lateinit var binding: FragmentPartnersScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPartnersScreenBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


}