package com.elmorshdi.technicaltask.data.model



 data class ProductResponse(
     val limit: Int?=null,
     val products: List<Product?>?=null,
     val skip: Int?=null,
     val total: Int?=null
)