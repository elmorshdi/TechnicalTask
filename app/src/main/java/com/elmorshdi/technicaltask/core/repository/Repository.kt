package com.elmorshdi.technicaltask.core.repository

import android.util.Log
import com.elmorshdi.technicaltask.data.model.ProductResponse
import com.elmorshdi.technicaltask.data.network.ApiService
import com.elmorshdi.technicaltask.data.repository.MainRepository
import com.elmorshdi.technicaltask.view.util.Resource
import javax.inject.Inject

class Repository@Inject constructor(private val api: ApiService) :MainRepository{
    override suspend fun getProducts(): Resource<ProductResponse> {
        return try {
            val response = api.getProducts()
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occurred", null)
            } else {
                Resource.error("An unknown error occurred", null)
            }
        } catch(e: Exception) {
            Log.e("EXCEPTION", "EXCEPTION:", e)
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}