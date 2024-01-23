package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.Destination
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.mainGreen
import com.example.littlelemon.ui.theme.secondaryColor
import com.example.littlelemon.utils.SharedPreferencesUtils

object ToastUtils {

    fun displayErrorMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun Onboarding(navController: NavController, modifier: Modifier = Modifier) {
    HeaderComponent(navController)
}

@Composable
fun HeaderComponent(navController: NavController) {
    val context = LocalContext.current
    Column {
        val firstNameState = remember { mutableStateOf("") }
        val lastNameState = remember { mutableStateOf("") }
        val emailState = remember { mutableStateOf("") }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageComponent(imagePainter = painterResource(id = R.drawable.logo), size = 180, height = 80)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .background(color = mainGreen)
                .padding(all = 40.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically,) {
            Text(text = "Let's Get To Know You", color = Color.White)
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
                TextButton("Register") {
                    if (isValidInput(firstNameState.value, lastNameState.value, emailState.value)) {
                        SharedPreferencesUtils.saveDataToSharedPreferences(context, firstNameState.value, lastNameState.value, emailState.value)

                        navController.navigate(Destination.DestinationsImpl.home)
                    } else {
                        ToastUtils.displayErrorMessage(context,"Registration unsuccessful. Please enter all data.")
                    }
                }
            }

        }
    }
}

@Composable
fun ImageComponent(imagePainter: Painter, size: Int, height: Int) {
    Image(
        painter = imagePainter,
        contentDescription = null,
        Modifier
            .width(width = size.dp)
            .height(height = height.dp)
    )
}

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = secondaryColor, contentColor = Color.Black),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Text(text = text)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonTextField(
    label: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {
        Column(
            verticalArrangement = Arrangement.Center, modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Text(text = label, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f), fontSize = 12.sp)
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = placeholder.ifEmpty { "" },
                onValueChange = { onValueChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(0.dp, MaterialTheme.colorScheme.background),
                placeholder = { Text(text = placeholder, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)) },
                textStyle = MaterialTheme.typography.bodyMedium,
            )

        }
}

fun isValidInput(firstName: String, lastName: String, email: String): Boolean {
    return firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()
}

fun displayErrorMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}