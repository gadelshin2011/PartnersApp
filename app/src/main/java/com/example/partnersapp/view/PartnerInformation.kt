package com.example.partnersapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.partnersapp.R
import com.example.partnersapp.databinding.FragmentPartnerInformationBinding
import com.example.partnersapp.databinding.RcItemTvBinding

class PartnerInformation : Fragment() {
lateinit var binding: FragmentPartnerInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPartnerInformationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonExit.setOnClickListener {
            //  findNavController().navigate(R.id.action_listPartnersInCategory_to_partnersScreen)
            findNavController().popBackStack()
        }
    }


}