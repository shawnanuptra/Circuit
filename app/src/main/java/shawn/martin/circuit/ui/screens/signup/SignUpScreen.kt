package shawn.martin.circuit.ui.screens.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shawn.martin.circuit.ui.theme.CircuitTheme
import shawn.martin.circuit.ui.theme.customTextFieldColors
import shawn.martin.circuit.util.Constants

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignUpScreen() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }
    var passworConfirmVisible by remember { mutableStateOf(false) }

    CircuitTheme {
        Scaffold(
            backgroundColor = MaterialTheme.colors.primary,

            ) { _ ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        vertical = Constants.SCREEN_VERTICAL_PADDING.dp,
                    ),
                verticalArrangement = Arrangement.SpaceBetween,

                ) {


                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxHeight(0.9F)
                        .fillMaxWidth()
                        .padding(
                            horizontal = Constants.SCREEN_HORIZONTAL_PADDING.dp,
                        )
                ) {

                    // Title
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Hello there!",
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Excited to work with you!", fontSize = 22.sp,
                            fontWeight = FontWeight.Normal,
                            overflow = TextOverflow.Visible,
                            textAlign = TextAlign.Center
                        )
                    }

                    // Fields
                    Column() {
                        // Email
                        OutlinedTextField(
                            colors = customTextFieldColors(),
                            value = email,
                            onValueChange = { email = it },
                            singleLine = true,
                            label = { Text("Email") },
                            placeholder = { Text(text = "circu.it@email.com") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Password
                        OutlinedTextField(
                            colors = customTextFieldColors(),
                            value = password,
                            onValueChange = { password = it },
                            visualTransformation = if (passwordVisible) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },
                            singleLine = true,
                            label = { Text("Password") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType =
                                KeyboardType.Password
                            ),
                            trailingIcon = {
                                if (passwordVisible) {
                                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                        Icon(imageVector = Icons.Filled.Visibility, "Hide password")
                                    }
                                } else {
                                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                        Icon(
                                            imageVector = Icons.Filled.VisibilityOff,
                                            "Show password"
                                        )
                                    }
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Confirm Password
                        OutlinedTextField(
                            colors = customTextFieldColors(),
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            visualTransformation = if (passworConfirmVisible) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },
                            singleLine = true,
                            label = { Text("Confirm Password") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType =
                                KeyboardType.Password
                            ),
                            trailingIcon = {
                                if (passworConfirmVisible) {
                                    IconButton(onClick = {
                                        passworConfirmVisible = !passworConfirmVisible
                                    }) {
                                        Icon(imageVector = Icons.Filled.Visibility, "Hide password")
                                    }
                                } else {
                                    IconButton(onClick = {
                                        passworConfirmVisible = !passworConfirmVisible
                                    }) {
                                        Icon(
                                            imageVector = Icons.Filled.VisibilityOff,
                                            "Show password"
                                        )
                                    }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }


                    // Login Button
                    Button(
                        onClick = {
                            // navigateToLogin()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    ) {
                        Text(text = "Sign Up", fontSize = 18.sp)
                    }

                    // Bottom Text

                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Text(text = "Already have an account?")
                    TextButton(onClick = {
                        //  navigateToLogin()
                    }) {
                        Text(
                            text = "Log In here.",
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline,
                            color = Color.White,
                            overflow = TextOverflow.Visible,
                            maxLines = 1
                        )
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}