package com.elmorshdi.technicaltask.view.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmorshdi.technicaltask.core.repository.Repository
import com.elmorshdi.technicaltask.data.model.Product
import com.elmorshdi.technicaltask.view.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val mainUiState: StateFlow<Status>
        get() = _mainUiState
    private val _mainUiState = MutableStateFlow<Status>(Status.Empty)


    val products: LiveData<List<Product>>
        get() = _products
    private val _products: MutableLiveData<List<Product>> = MutableLiveData()
    val error: LiveData<String>
        get() = _error
    private val _error: MutableLiveData<String> = MutableLiveData()


    private fun getProductsList() {
        viewModelScope.launch(Dispatchers.Main) {
            _mainUiState.value = Status.LOADING
            val response = repository.getProducts()
            when (response.status) {
                Status.SUCCESS -> {
                    _mainUiState.value = Status.SUCCESS
                    _products.value = response.data!!.products!!
                }
                Status.LOADING -> {
                    _mainUiState.value = Status.LOADING
                }
                Status.ERROR -> {
                    _mainUiState.value = Status.ERROR
                    _error.value = response.message!!
                }
                else -> {
                }
            }
        }
    }

    init {
        getProductsList()

    }
}