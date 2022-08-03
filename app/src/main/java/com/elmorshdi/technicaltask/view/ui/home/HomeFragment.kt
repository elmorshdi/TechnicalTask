package com.elmorshdi.technicaltask.view.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.elmorshdi.technicaltask.data.model.Product
import com.elmorshdi.technicaltask.databinding.FragmentHomeBinding
import com.elmorshdi.technicaltask.view.ui.adapter.ProductAdapter
import com.elmorshdi.technicaltask.view.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class HomeFragment : Fragment(),ProductAdapter.Interaction {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

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
                        viewModel.products.observe(viewLifecycleOwner){
                            setUpRecyclerView(it)

                        }
                    }
                    else -> UInt
                }
            }}
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
        Log.d("listttt",value.toString())

        binding.mainRecycler.adapter = adapter
    }

    override fun onItemSelected(product: Product) {
        TODO("Not yet implemented")
    }
}