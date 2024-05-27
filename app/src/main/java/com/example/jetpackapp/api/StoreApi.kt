package com.example.jetpackapp.api

import com.example.jetpackapp.models.Product
import com.example.jetpackapp.models.ProductBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {

    @GET("/products")
    suspend  fun getProducts(): Response<List<Product>>

    @GET("/products/{productId}")
    suspend fun getProductById(
        @Path("productId") productId: Int
    ): Response<Product>

    @GET("/products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String
    ) : Response<List<Product>>
}