package com.elmorshdi.technicaltask.repository

import com.elmorshdi.technicaltask.data.model.ProductResponse
import com.elmorshdi.technicaltask.data.repository.MainRepository
import com.elmorshdi.technicaltask.view.util.Resource
import com.elmorshdi.technicaltask.view.util.Status

class FakeRepository : MainRepository {
    private var shouldReturnNetworkError = false
    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getProducts(): Resource<ProductResponse> {
        return if (shouldReturnNetworkError) {
            Resource(status = Status.ERROR, message = "error occurred")
        } else {
            Resource(Status.SUCCESS, ProductResponse(null, ArrayList(), 10, 0))
        }
    }


}