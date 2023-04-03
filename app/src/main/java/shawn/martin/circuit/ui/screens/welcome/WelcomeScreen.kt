package shawn.martin.circuit.ui.screens.welcome

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shawn.martin.circuit.R
import shawn.martin.circuit.ui.theme.CircuitTheme
import shawn.martin.circuit.util.Constants.SCREEN_HORIZONTAL_PADDING
import shawn.martin.circuit.util.Constants.SCREEN_VERTICAL_PADDING

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WelcomeScreen(
    navigateToLogin: () -> Unit,
    navigateToSignup: () -> Unit,

    ) {
    CircuitTheme {
        Scaffold(
            backgroundColor = MaterialTheme.colors.primary,

            ) { _ ->
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = SCREEN_HORIZONTAL_PADDING.dp,
                        vertical = SCREEN_VERTICAL_PADDING.dp,
                    )
            ) {
                Image(
                    painterResource(id = R.drawable.welcome_logo),
                    contentDescription = "Circu.it Logo"
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    Button(
                        onClick = {
                            navigateToLogin()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    ) {
                        Text(text = "Log In", fontSize = 18.sp)
                    }

                    OutlinedButton(
                        onClick = {
                            navigateToSignup()
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White),
                        border = BorderStroke(1.dp, Color.White)
                    ) {
                        Text(text = "Sign Up", fontSize = 18.sp)
                    }
                }

            }

        }
    }
}


@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen({}, {})
}