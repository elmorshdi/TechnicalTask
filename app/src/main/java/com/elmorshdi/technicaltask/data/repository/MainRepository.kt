package com.elmorshdi.technicaltask.data.repository

import com.elmorshdi.technicaltask.data.model.ProductResponse
import com.elmorshdi.technicaltask.view.util.Resource


interface MainRepository {
    suspend fun getProducts(): Resource<ProductResponse>
}