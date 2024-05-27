package com.example.jetpackapp.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackapp.navigation.NavigationItem
import kotlinx.coroutines.launch


@Composable
fun TopBar(drawerState: DrawerState, currentRoute : String) {
    val scope = rememberCoroutineScope()
    Box (modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(Color.LightGray), contentAlignment = Alignment.Center ){
        Row (modifier = Modifier.padding(5.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            Image(imageVector = Icons.Default.Menu, contentDescription = "",
                modifier = Modifier.clickable {
                    scope.launch {
                        drawerState.open()
                    }
                })
            Text(text = currentRoute, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            ContactProfile(modifier = Modifier)
        }
    }
}

@Composable
fun ContactProfile(modifier: Modifier){
    Box(modifier = Modifier
        .clip(shape = CircleShape)
        .border(width = 2.dp, shape = CircleShape, color = Color.Black)
    ) {
        Image(imageVector = Icons.Default.Person, contentDescription = "")
    }
}