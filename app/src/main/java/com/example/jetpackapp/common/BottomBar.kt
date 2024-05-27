package com.example.jetpackapp.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackapp.navigation.Screens
import kotlinx.coroutines.launch

@Composable
fun BottomBar(navController: NavHostController){
    Box (modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(Color.LightGray), contentAlignment = Alignment.Center ){
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Image(imageVector = Icons.Default.Home, contentDescription = "",
                modifier = Modifier.clickable {
                    navController.navigate(Screens.Home.route)
                }
            )

            Image(imageVector = Icons.Default.ShoppingCart, contentDescription = "",
                modifier = Modifier.clickable {

                }
            )
        }
    }
}