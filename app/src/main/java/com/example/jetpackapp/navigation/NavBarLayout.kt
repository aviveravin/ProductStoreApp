package com.example.jetpackapp.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpackapp.R

@Composable
fun NavBarHeader(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.avi) , contentDescription = "",
            modifier = Modifier
                .size(100.dp)
                .padding(top = 100.dp))
        Text(text = "Avinash", modifier = Modifier.padding(100.dp))
    }
}

@Composable
fun NavBarBody(
    items: List<NavigationItem>,
    currentRoute: String?,
    onClick: (NavigationItem) -> Unit
) {
    items.forEachIndexed { index, navigationItem ->
        NavigationDrawerItem(label = {
            Text(text = navigationItem.title)
        },
            selected = currentRoute == navigationItem.route,
            onClick = { onClick(navigationItem) },
            icon = {
                Icon(
                    imageVector = if (currentRoute == navigationItem.route) {
                        navigationItem.selectedIcon
                    } else {
                        navigationItem.unselectedIcon
                    }, contentDescription = ""
                )
            },
            modifier = Modifier.padding(
                PaddingValues(horizontal = 12.dp,
                    vertical = 8.dp)
            ))
    }
}