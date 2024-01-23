package com.example.littlelemon

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.Destination
import com.example.littlelemon.utils.SharedPreferencesUtils

@Preview
@Composable
fun PreviewHomeScreen(){
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}

@Composable
fun HomeScreen(navController: NavController) {
    Surface {
        val context = LocalContext.current
        Column {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "")
                ImageComponent(imagePainter = painterResource(id = R.drawable.logo), size = 180, height = 80)
                Box(modifier = Modifier.clickable(onClick =  {navController.navigate(Destination.DestinationsImpl.profile)})){
                    ImageComponent(imagePainter = painterResource(id = R.drawable.profile), size = 50, height = 50)
                }
            }


        }
    }

}
