package shawn.martin.circuit.ui.screens.login

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import shawn.martin.circuit.data.Resource
import shawn.martin.circuit.ui.theme.CircuitTheme
import shawn.martin.circuit.ui.theme.customTextFieldColors
import shawn.martin.circuit.ui.viewmodels.SharedViewModel
import shawn.martin.circuit.util.Constants

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LogInScreen(
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    sharedViewModel: SharedViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope();

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }

    val logInFlow = sharedViewModel.logInFlow.collectAsState()

    var validationMessage by remember { mutableStateOf("") }


    fun validateForm(email: String, password: String) {
        // reset validationMessage everytime validateForm() is called
        validationMessage = ""

        // Validate Email
        val result = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        if (!result) {
            validationMessage = "Please enter a correct email address"
        }
        // Validate Password
        else if (password.length < 6) {
            // password < 6 chars
            validationMessage = "Please enter a password with a minimum of 6 characters"
        }
    }

    CircuitTheme {
        Scaffold(
            backgroundColor = MaterialTheme.colors.primary,
            scaffoldState = scaffoldState,

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
                            text = "Welcome,",
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "happy to have you back!", fontSize = 22.sp,
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
                        Text(
                            text = "Forgot password?",
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                        )
                    }


                    // Login Button
                    Button(
                        onClick = {
                            validateForm(email, password)
                            if (validationMessage == "") {
                                sharedViewModel.logIn(email, password)
                            } else {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = validationMessage,
                                        actionLabel = "OK"
                                    )
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    ) {
                        Text(text = "Log In", fontSize = 18.sp)
                    }

                    // Bottom Text

                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Text(text = "Don't have an account?")
                    TextButton(onClick = {
                          navigateToSignUp()
                    }) {
                        Text(
                            text = "Sign Up here.",
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline,
                            color = Color.White,
                            overflow = TextOverflow.Visible,
                            maxLines = 1
                        )
                    }

                }

                logInFlow.value.let {
                    when (it) {
                        is Resource.Failure -> {
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = it.exception.message!!,
                                    actionLabel = "OK"
                                )
                            }
                            sharedViewModel.resetLogInFlow()
                        }
                        is Resource.Loading -> {
                            CircularProgressIndicator()
                        }
                        is Resource.Success -> {
                            LaunchedEffect(Unit) {
                                navigateToHome()
                            }
                        }
                        else -> {}
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun LogInScreenPreview() {
//    LogInScreen()
}