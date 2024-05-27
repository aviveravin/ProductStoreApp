package com.example.jetpackapp.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpackapp.ViewState
import com.example.jetpackapp.models.Product
import com.example.jetpackapp.viewmodels.ProductViewModel

@Composable
fun HomeScreen(innerPadding: PaddingValues, navController: NavController) {
    val productViewModel: ProductViewModel = hiltViewModel()
    val productState = productViewModel.getAllProductsFlow.collectAsState(initial = ViewState.loading())

    when (val state = productState.value) {
        is ViewState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ViewState.Success -> {
            // Show the products
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxSize(),
                content = {
                    items(state.data) { product ->
                        HomeProductItem(product = product, navController = navController)
                    }
                }
            )
        }
        is ViewState.Failed -> {
            // Show an error message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Failed to load products: ${state.message}")
            }
        }
        is ViewState.NetworkFailed -> {
            // Show a network error message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Network error: ${state.message}")
            }
        }
        is ViewState.ExceptionError -> {
            // Show an exception error message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "An error occurred: ${state.message}")
            }
        }

        is ViewState.ErrorsData -> TODO()
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeProductItem(product: Product, navController: NavController) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(160.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxSize()
            .border(5.dp, Color(0xFFEEEEEE))
            .clickable { navController.navigate("product_detail_screen/${product.id}") },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            GlideImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .padding(15.dp)
            )
            Text(
                text = product.title,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }

}
