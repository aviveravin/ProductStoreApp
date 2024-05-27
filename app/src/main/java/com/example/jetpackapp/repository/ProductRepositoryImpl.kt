package com.example.jetpackapp.repository

import com.example.jetpackapp.ResponseResult
import com.example.jetpackapp.models.Product
import javax.inject.Singleton

@Singleton
interface ProductRepositoryImpl {

    suspend fun getProducts() : ResponseResult<List<Product>>
    suspend fun getProductById(productId: Int) : ResponseResult<Product>
    suspend fun getProductsByCategory(category: String) : ResponseResult<List<Product>>
}