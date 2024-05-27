package com.example.jetpackapp.navigation

sealed class Screens(var route: String) {
    object Home: Screens("home")
    object Profile: Screens("profile")
    object Notification: Screens("notification")
    object Setting: Screens("setting")
    object ProductDetail: Screens("productdetail")
}