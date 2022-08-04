package com.elmorshdi.technicaltask.view.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.elmorshdi.technicaltask.data.model.Product
import com.elmorshdi.technicaltask.databinding.FragmentHomeBinding
import com.elmorshdi.technicaltask.view.adapter.ProductAdapter
import com.elmorshdi.technicaltask.view.util.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint

class HomeFragment : Fragment(),ProductAdapter.Interaction {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {

            viewModel.mainUiState.collect { event ->
                when (event) {
                    is Status.LOADING ->
                        progressbarVisible(true)

                    is Status.ERROR -> {
                      errorVisible(true)
                        binding.errorText.text = viewModel.error.value
                    }
                    is Status.SUCCESS -> {
                       recyclerVisible(true)
                        viewModel.products.observeOnce(viewLifecycleOwner){
                            setUpRecyclerView(it)

                        }
                    }
                    else -> UInt
                }
            }}
        binding.signOutArrow.setOnClickListener {
            alertDialog(
                "sign Out", "Are you sure you want to sign out ?",
                it.context, ::signOut, it
            )

         }
    }

    private fun signOut(view: View) {
        SharedPreferencesManager.signOutShared(sharedPreferences.edit())
        val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
        view.findNavController().navigate(action)
    }

    private fun progressbarVisible(b: Boolean) {
            binding.spinKit.isVisible = b
            binding.errorText.isVisible=!b
            binding.mainRecycler.isVisible=!b

    }
    private fun errorVisible(b: Boolean) {
        binding.spinKit.isVisible =! b
        binding.errorText.isVisible= b
        binding.mainRecycler.isVisible=!b

    }
    private fun recyclerVisible(b: Boolean) {
        binding.spinKit.isVisible = !b
        binding.errorText.isVisible=!b
        binding.mainRecycler.isVisible= b

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
    private fun setUpRecyclerView(value: List<Product>?) {
         val adapter = ProductAdapter(interaction = this)
        adapter.submitList(value)
        binding.mainRecycler.adapter = adapter
    }

    override fun onItemSelected(product: Product) {

        showDialog(product,
        requireContext(),
        layoutInflater)

    }
}