package com.example.littlelemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import com.example.littlelemon.ui.theme.mainGreen
import com.example.littlelemon.utils.SharedPreferencesUtils


@Preview
@Composable
fun PreviewProfileScreen(){
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}

@Composable
fun ProfileScreen(navController: NavController) {
    Surface {
        val context = LocalContext.current
        Column {
            val firstNameState = remember { mutableStateOf(SharedPreferencesUtils.getFirstNameFromSharedPreferences(context)) }
            val lastNameState = remember { mutableStateOf(SharedPreferencesUtils.getLastNameFromSharedPreferences(context)) }
            val emailState = remember { mutableStateOf(SharedPreferencesUtils.getEmailFromSharedPreferences(context)) }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageComponent(imagePainter = painterResource(id = R.drawable.logo), size = 180, height=80)
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(all = 20.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically,) {

            }
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(all = 20.dp), verticalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(text = "Personal Information")

                    LemonTextField(
                        label = "First Name",
                        placeholder = "Your First Name",
                        onValueChange = { firstNameState.value = it }
                    )
                    LemonTextField(
                        label = "Last Name",
                        placeholder = "Your Last Name",
                        onValueChange = { lastNameState.value = it }
                    )
                    LemonTextField(
                        label = "Email",
                        placeholder = "Your Email",
                        onValueChange = { emailState.value = it }
                    )
                }

                Column {
                    TextButton("Log Out") {

                            SharedPreferencesUtils.clearSharedPreferences(context)
                            navController.navigate(Destination.DestinationsImpl.onboarding)

                    }
                }

            }
        }
    }

}
