package com.example.partnersapp.view.list_partner_in_category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.partnersapp.databinding.FragmentListPartnersInCategoryBinding
import com.example.partnersapp.model.partnerModels.category.PartnerCategoryDetail
import com.example.partnersapp.presentation.adapter.AdapterPartners
import com.example.partnersapp.presentation.adapter.ShowCategoryAdapter
import com.example.partnersapp.view.list_partner_in_category.viewModel.ListPartnersInCategoryViewModel
import com.example.partnersapp.view.partnersScreen.viewModel.PartnersViewM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListPartnersInCategory : Fragment() {
    lateinit var binding: FragmentListPartnersInCategoryBinding
    private lateinit var currentModel: PartnerCategoryDetail
    private val adapterShowPartnersFromCategory = ShowCategoryAdapter()
    private val viewModel: ListPartnersInCategoryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListPartnersInCategoryBinding.inflate(inflater, container, false)
        currentModel = requireArguments().getParcelable<PartnerCategoryDetail>("category")!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvScreenName.text = currentModel.title
        binding.rcViewPartners.adapter = adapterShowPartnersFromCategory

        setListener()
        showPartners()


    }

    private fun showPartners() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listPartners.collect {
                    adapterShowPartnersFromCategory.setList(it)

                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch{
            val info = viewModel.requestPartnersInCategory(currentModel.id)
            if (info != "Ok"){
                Toast.makeText(context, info, Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun setListener() {
        binding.closeButton.setOnClickListener {
                //  findNavController().navigate(R.id.action_listPartnersInCategory_to_partnersScreen)
            findNavController().popBackStack()
        }
    }

    companion object {
        fun getBundle(result: PartnerCategoryDetail): Bundle {
            return bundleOf("category" to result)
        }

//        fun getBundleCategoryNew(results: CategoryNew) : Bundle{
//            return bundleOf("categoryN" to results)
//        }
    }


}