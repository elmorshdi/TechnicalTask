package com.elmorshdi.technicaltask.data.network

import com.elmorshdi.technicaltask.data.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): Response<ProductResponse>
}
