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
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.partnersapp.R
import com.example.partnersapp.databinding.FragmentPartnersScreenBinding
import com.example.partnersapp.model.partnerModels.TextViewModel
import com.example.partnersapp.presentation.adapter.AdapterPartners
import com.example.partnersapp.presentation.adapter.AdapterPartnersCategory
import com.example.partnersapp.presentation.adapter.AdapterTextView
import kotlinx.coroutines.launch

class PartnersScreen : Fragment() {
    lateinit var binding: FragmentPartnersScreenBinding
    private val adapterAllPartners = AdapterPartners()
    private val adapterCategoryPartners = AdapterPartnersCategory()
    private val adapterTextView = AdapterTextView()
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
        adapterTextView.setListTV(listOf(TextViewModel()))
        init()
    }

    private fun init() {


        val concatAdapter = ConcatAdapter(adapterCategoryPartners, adapterTextView, adapterAllPartners)
        binding.rcViewPartners.layoutManager = GridLayoutManager(context, 2)
        binding.rcViewPartners.adapter = concatAdapter

        (binding.rcViewPartners.layoutManager as GridLayoutManager).spanSizeLookup = object :
            GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position < adapterCategoryPartners.itemCount) {
                    1
                } else {
                    2
                }
            }
        }

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
                viewModel.partnersCategory.collect {
                    adapterCategoryPartners.setListCategory(it)
                }
            }
        }
        viewModel.requestPartnerCategory()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.partners.collect {
                    adapterAllPartners.setList(it)

                }
            }
        }
        viewModel.requestPartners()


    }
}