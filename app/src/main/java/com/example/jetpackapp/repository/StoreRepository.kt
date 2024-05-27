package com.example.jetpackapp.repository
import android.util.Log
import com.example.jetpackapp.ResponseResult
import com.example.jetpackapp.api.StoreApi
import com.example.jetpackapp.getResponse
import com.example.jetpackapp.models.Product
import com.example.jetpackapp.models.ProductBody
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreRepository @Inject constructor(private val storeApi: StoreApi): ProductRepositoryImpl {

//    private val _products = MutableStateFlow<List<Product>>(emptyList())
//    val products : StateFlow<List<Product>>
//        get() = _products
//
//    private val _productById = MutableStateFlow<List<Product>>(emptyList())
//    val productById : StateFlow<List<Product>>
//        get() = _productById
//
//    suspend fun getProducts(){
//        val response = storeApi.getProducts()
//        if (response.isSuccessful && response.body() != null){
//            _products.emit(response.body()!!)
//        }
//    }
//
//    suspend fun getProductById(productBody: ProductBody){
//        val response = storeApi.getProductById(productBody)
//        if (response.isSuccessful && response.body() != null){
//            _productById.emit(response.body()!!)
//        }
//    }

    // Fetch all the products
    override suspend fun getProducts(): ResponseResult<List<Product>> {
        return try {
            val response = storeApi.getProducts().getResponse()
            when (response.isNotEmpty()) {
                true -> {
                    ResponseResult.success(response)
                }
                else -> ResponseResult.error(response.toString())
            }
        } catch (e: HttpException) {
            ResponseResult.networkError("Constants.ERROR_MESSAGE")
        } catch (connection: java.net.ConnectException) {
            ResponseResult.networkError(connection.toString())
        } catch (e: Exception) {
            Log.d(
                "StoreRepository",
                "#initializeObservers getAllProductsFlow: viewState =, Exception e = $e"
            )
            ResponseResult.exception("Constants.ERROR_MESSAGE")
        }
    }

    //Fetch product using id
    override suspend fun getProductById(productId: Int): ResponseResult<Product> {
        return try {
            val response = storeApi.getProductById(productId).getResponse()
            when {
                true ->ResponseResult.success(response)
                else -> ResponseResult.error(response.toString())
            }
        } catch (e: HttpException) {
            ResponseResult.networkError("Constants.ERROR_MESSAGE")
        } catch (connection: java.net.ConnectException) {
            ResponseResult.networkError(connection.toString())
        } catch (e: Exception) {
            Log.d(
                "StoreRepository",
                "#initializeObservers getProductByIdFlow: viewState =, Exception e = $e"
            )
            ResponseResult.exception("Constants.ERROR_MESSAGE")
        }
    }

    //Fetch Products list by category
    override suspend fun getProductsByCategory(category: String): ResponseResult<List<Product>> {
        return try {
            val response = storeApi.getProductsByCategory(category).getResponse()
            when {
                true ->ResponseResult.success(response)
                else -> ResponseResult.error(response.toString())
            }
        } catch (e: HttpException) {
            ResponseResult.networkError("Constants.ERROR_MESSAGE")
        } catch (connection: java.net.ConnectException) {
            ResponseResult.networkError(connection.toString())
        } catch (e: Exception) {
            Log.d(
                "StoreRepository",
                "#initializeObservers getProductByCategoryFlow: viewState =, Exception e = $e"
            )
            ResponseResult.exception("Constants.ERROR_MESSAGE")
        }
    }
}