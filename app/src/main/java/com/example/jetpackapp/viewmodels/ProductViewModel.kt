package com.example.jetpackapp.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackapp.ResponseResult
import com.example.jetpackapp.ViewState
import com.example.jetpackapp.models.Product
import com.example.jetpackapp.repository.StoreRepository
import com.example.jetpackapp.shareWhileObserved
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val storeRepository: StoreRepository
) : ViewModel() {

    private val _getAllProductsFlow = MutableSharedFlow<ViewState<List<Product>>>()
    val getAllProductsFlow: SharedFlow<ViewState<List<Product>>> =
        _getAllProductsFlow.shareWhileObserved(viewModelScope)

    private val _getProductByIdFlow = MutableSharedFlow<ViewState<Product>>()
    val getProductByIdFlow: SharedFlow<ViewState<Product>> =
        _getProductByIdFlow.shareWhileObserved(viewModelScope)

    private val _getProductsByCategory = MutableSharedFlow<ViewState<List<Product>>>()
    val getProductsByCategory: SharedFlow<ViewState<List<Product>>> =
        _getProductsByCategory.shareWhileObserved(viewModelScope)

    init {
        getAllProducts()
    }

    fun getAllProducts() {
        viewModelScope.launch {
            _getAllProductsFlow.emit(ViewState.loading())
            val viewState = when (val responseState =
                storeRepository.getProducts()) {
                is ResponseResult.Success -> ViewState.success(responseState.data)
                is ResponseResult.Error -> ViewState.failed(responseState.message)
                is ResponseResult.NetworkException -> ViewState.NetworkFailed(responseState.networkError)
                is ResponseResult.ErrorException -> ViewState.exceptionError(responseState.exception)
                else -> ViewState.exceptionError(responseState.toString())
            }
            _getAllProductsFlow.emit(viewState)
        }
    }

    fun getProductById(productId: Int) {
        viewModelScope.launch {
            _getProductByIdFlow.emit(ViewState.loading())
            val viewState = when (val responseState =
                storeRepository.getProductById(productId)) {
                is ResponseResult.Success -> ViewState.success(responseState.data)
                is ResponseResult.Error -> ViewState.failed(responseState.message)
                is ResponseResult.NetworkException -> ViewState.NetworkFailed(responseState.networkError)
                is ResponseResult.ErrorException -> ViewState.exceptionError(responseState.exception)
                else -> ViewState.exceptionError(responseState.toString())
            }
            _getProductByIdFlow.emit(viewState)
        }
    }

    fun getProductsByCategory(category: String) {
        viewModelScope.launch {
            _getProductsByCategory.emit(ViewState.loading())
            val viewState = when (val responseState =
                storeRepository.getProductsByCategory(category)) {
                is ResponseResult.Success -> ViewState.success(responseState.data)
                is ResponseResult.Error -> ViewState.failed(responseState.message)
                is ResponseResult.NetworkException -> ViewState.NetworkFailed(responseState.networkError)
                is ResponseResult.ErrorException -> ViewState.exceptionError(responseState.exception)
                else -> ViewState.exceptionError(responseState.toString())
            }
            _getProductsByCategory.emit(viewState)
        }
    }
}