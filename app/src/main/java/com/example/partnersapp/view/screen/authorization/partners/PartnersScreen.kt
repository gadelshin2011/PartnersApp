package com.example.partnersapp.view.screen.authorization.partners

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.partnersapp.R
import com.example.partnersapp.databinding.FragmentPartnersScreenBinding
import com.example.partnersapp.presentation.adapter.AdapterPartners
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PartnersScreen : Fragment() {
    lateinit var binding: FragmentPartnersScreenBinding
    private val adapterRc = AdapterPartners()
    private val viewModel: PartnersViewM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPartnersScreenBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.rcViewPartners.adapter = adapterRc
        showData()
        setListener()
    }

    private fun setListener() {
        binding.imageButtonBack.setOnClickListener {
            findNavController().navigate(R.id.action_partnersScreen_to_authorization)
        }
    }

    private fun showData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.partners.collect {
                    adapterRc.setList(it)
                }
            }
        }
        viewModel.requestPartners()
    }
}