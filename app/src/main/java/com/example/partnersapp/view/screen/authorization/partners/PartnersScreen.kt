package com.example.partnersapp.view.screen.authorization.partners

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.partnersapp.R
import com.example.partnersapp.databinding.FragmentPartnersScreenBinding
import com.example.partnersapp.presenter.adapter.RcAdapterPartners
import com.example.partnersapp.presenter.network.WebRepository
import com.example.partnersapp.view.screen.authorization.AuthViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PartnersScreen : Fragment() {
    lateinit var binding: FragmentPartnersScreenBinding
    private lateinit var recyclerView: RecyclerView
    var webRepo = WebRepository()
    private val adapterRc = RcAdapterPartners()
    private val viewModel: AuthViewModel by activityViewModels()

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
        initialization()
        showData()
        setListener()
    }

    private fun setListener() {
        binding.imBtnBack.setOnClickListener {
            findNavController().navigate(R.id.action_partnersScreen_to_authorization)
        }
    }

    private fun showData() {

        viewModel.partners.onEach {
            adapterRc.setList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

//        binding.tvScreenName.setOnClickListener {
//            viewModel.requestPartners()
//        }

        viewModel.requestPartners()


    }

    private fun initialization() {
        recyclerView = binding.rcViewPartners
        recyclerView.adapter = adapterRc
    }


}