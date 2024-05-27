package com.example.jetpackapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapp.api.StoreApi
import com.example.jetpackapp.common.BottomBar
import com.example.jetpackapp.common.TopBar
import com.example.jetpackapp.navigation.NavBarBody
import com.example.jetpackapp.navigation.NavBarHeader
import com.example.jetpackapp.navigation.NavigationItem
import com.example.jetpackapp.navigation.Screens
import com.example.jetpackapp.navigation.SetupNavGraph
import com.example.jetpackapp.ui.theme.JetpackAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var storeApi: StoreApi
    @OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch {
            var response = storeApi.getProducts()
            Log.d("aviveravin", "onCreate: ${response.body().toString()}")
        }
        setContent {
            JetpackAppTheme {
                val items = listOf(
                    NavigationItem(
                        title = "Home",
                        route = Screens.Home.route,
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home
                    ),
                    NavigationItem(
                        title = "Electronics",
                        route = Screens.Profile.route,
                        selectedIcon = Icons.Filled.Person,
                        unselectedIcon = Icons.Outlined.Person
                    ),
                    NavigationItem(
                        title = "Jewelery",
                        route = Screens.Notification.route,
                        selectedIcon = Icons.Filled.Notifications,
                        unselectedIcon = Icons.Outlined.Notifications
                    ),
                    NavigationItem(
                        title = "Men's clothing",
                        route = Screens.Setting.route,
                        selectedIcon = Icons.Filled.Settings,
                        unselectedIcon = Icons.Outlined.Settings
                    ),
                    NavigationItem(
                        title = "Women's clothing",
                        route = Screens.Setting.route,
                        selectedIcon = Icons.Filled.Settings,
                        unselectedIcon = Icons.Outlined.Settings
                    )
                )
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet {
                            NavBarHeader()
                            NavBarBody(items = items, currentRoute = currentRoute) {currentNavigationItem ->
                                navController.navigate(currentNavigationItem.route)
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        }
                    },
                    drawerState = drawerState) {
                    Scaffold(
                    topBar = {
                        TopBar(drawerState, currentRoute.toString())

                    },
                        bottomBar = {
                            BottomBar(navController)
                        }
                ) {innerPadding ->
                    SetupNavGraph(navHostController = navController, innerPadding = innerPadding)
                }

                }
            }
        }
    }
}



