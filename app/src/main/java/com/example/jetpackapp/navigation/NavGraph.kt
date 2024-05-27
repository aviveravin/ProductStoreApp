package com.example.jetpackapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.jetpackapp.screens.HomeScreen
import com.example.jetpackapp.screens.NotificationScreen
import com.example.jetpackapp.screens.ProductDetailScreen
import com.example.jetpackapp.screens.ProfileScreen
import com.example.jetpackapp.screens.SettingScreen

@Composable
fun SetupNavGraph(
    navHostController: NavHostController,
    innerPadding: PaddingValues
){
    NavHost(navController = navHostController, startDestination = Screens.Home.route) {
        composable(Screens.Home.route){
            HomeScreen(innerPadding = innerPadding, navHostController)
        }
        composable(Screens.Profile.route){
            ProfileScreen(innerPadding = innerPadding)
        }
        composable(Screens.Setting.route){
            SettingScreen(innerPadding = innerPadding)
        }
        composable(Screens.Notification.route){
            NotificationScreen(innerPadding = innerPadding)
        }
        composable(
            "product_detail_screen/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ){ backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: return@composable
            ProductDetailScreen(productId)
        }
    }
}