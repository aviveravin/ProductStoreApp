package com.example.jetpackapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpackapp.ViewState
import com.example.jetpackapp.models.Product
import com.example.jetpackapp.viewmodels.ProductViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetailScreen(innerPadding: PaddingValues, productId: Int) {

    val productViewModel: ProductViewModel = hiltViewModel()

    LaunchedEffect(productId) {
        productViewModel.getProductById(productId)
    }
    val productState by productViewModel.getProductByIdFlow.collectAsState(initial = ViewState.loading())

    when (val state = productState) {
        is ViewState.Loading -> {
            Log.d("aviveravin", "ProductDetailScreen: Loading")
        }
        is ViewState.Success -> {
            val product = state.data
            Log.d("aviveravin", "ProductDetailScreen: Success - $product")

            ProductDetail(innerPadding,product)
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(top = 80.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                GlideImage(
//                    model = product.image,
//                    contentDescription = product.title,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .size(250.dp)
//                        .padding(5.dp),
//                )
//                Text(
//                    text = product.title,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 20.sp,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.padding(15.dp)
//                )
//                Text(
//                    text = "Price - Rs." + product.price.toString(),
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 20.sp,
//                )
//                Text(
//                    text = product.description,
//                    fontSize = 15.sp,
//                    color = Color.Gray,
//                    modifier = Modifier.padding(20.dp)
//                )
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .fillMaxHeight()
//                        .padding(bottom = 16.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Bottom
//                ) {
//                    ElevatedButton(
//                        onClick = { /* Handle checkout */ },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(8.dp),
//                        colors = ButtonDefaults.buttonColors(Color.Black)
//                    ) {
//                        Text(text = "Checkout", color = Color.White)
//                    }
//                }
//            }
        }
        is ViewState.Failed -> {
            Log.d("aviveravin", "ProductDetailScreen: Failed - ${state.message}")
            Text(text = state.message)
        }
        is ViewState.ErrorsData -> {
            Log.d("aviveravin", "ProductDetailScreen: ErrorsData - ${state.errorData}")
            Text(text = "Error: ${state.errorData}")
        }
        is ViewState.ExceptionError -> {
            Log.d("aviveravin", "ProductDetailScreen: ExceptionError - ${state.message}")
            Text(text = state.message)
        }
        is ViewState.NetworkFailed -> {
            Log.d("aviveravin", "ProductDetailScreen: NetworkFailed - ${state.message}")
            Text(text = state.message)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetail(innerPadding: PaddingValues, product: Product) {
    Box(

        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
            .padding(innerPadding)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), colors = CardDefaults.cardColors(Color.White)
            ) {
                GlideImage(
                    model = product.image,
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp),
                    alignment = Alignment.TopCenter
                )
//                Image(
//                    painter = painterResource(id = R.drawable.avi),
//                    contentDescription = "",
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .weight(0.5f)
//                        .padding(35.dp),
//                    alignment = Alignment.TopCenter
//                )
            }
            Text(
                text = product.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(15.dp)
            )
            Text(
                text = "Price - Rs." + product.price.toString(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(15.dp),
                fontSize = 20.sp,
            )
            Text(
                text = product.description,
                fontSize = 15.sp,
                color = Color.Gray,
                modifier = Modifier.padding(20.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            AddToCartButton(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun AddToCartButton(modifier: Modifier) {
    Box(
        modifier = Modifier
            .height(60.dp)
            .background(Color.Black)
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
    ) {
        Text(
            text = "Add To Cart",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}



