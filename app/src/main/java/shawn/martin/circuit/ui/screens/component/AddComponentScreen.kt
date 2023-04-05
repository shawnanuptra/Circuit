package shawn.martin.circuit.ui.screens.component

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import shawn.martin.circuit.model.Component
import shawn.martin.circuit.ui.theme.CircuitTheme
import shawn.martin.circuit.ui.viewmodels.FirestoreViewModel
import shawn.martin.circuit.util.Constants.SCREEN_HORIZONTAL_PADDING

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddComponentScreen(
    navigateToHome: () -> Unit,
//    sharedViewModel: SharedViewModel = hiltViewModel(),
    firestoreViewModel: FirestoreViewModel = hiltViewModel(),
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price: String by remember { mutableStateOf("") }

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope();

    var validationMessage by remember { mutableStateOf("") }

    fun validateForm(name: String, description: String, price: String) {
        // reset validation message if all fields are completed
        validationMessage = ""

        if (name.isEmpty() || description.isEmpty() || price.isEmpty()) {
            validationMessage = "Please enter all the information"
        }
        else {
            try {
                price.toDouble()
            } catch (e: Exception) {
                validationMessage = "Please enter a correct price"
            }
        }
    }


    CircuitTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = { Text("Add Component") },
                    backgroundColor = MaterialTheme.colors.primary,
                    navigationIcon = {
                        IconButton(onClick = { navigateToHome() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Cancel"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            //TODO: Update Component in Firestore
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Update Component"
                            )
                        }
                    }
                )
            },
        ) { _ ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = SCREEN_HORIZONTAL_PADDING.dp,
                        vertical = 20.dp,
                    ),
                verticalArrangement = Arrangement.spacedBy(32.dp)

            ) {
                // COMPONENT FIELD
                Column(
                ) {
                    Text("Component", style = MaterialTheme.typography.h6)
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = name,
                        onValueChange = { name = it },
                        singleLine = true,
                        placeholder = { Text(text = "e.g. HyperX DDR4 16GB RAM") },
                    )
                }

                // DESCRIPTION FIELD
                Column() {
                    Text("Description", style = MaterialTheme.typography.h6)
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = description,
                        onValueChange = { description = it },
                        placeholder = { Text(text = "e.g. DDR4 speed RAM 3600 MHz") },
                    )
                }

                // PRICE FIELD
                Column() {
                    Text("Price", style = MaterialTheme.typography.h6)
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("£", style = MaterialTheme.typography.h6)
                        Spacer(modifier = Modifier.size(10.dp))
                        OutlinedTextField(
                            value = price,
                            onValueChange = {
                                price = it
                            },
                            placeholder = { Text(text = "e.g. 300") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal
                            )
                        )
                    }

                }


                // UPLOAD MEDIA
                OutlinedButton(
                    onClick = {
//                    navigateToSignup()
                    }, modifier = Modifier
                        .fillMaxWidth(),
                    border = BorderStroke(1.dp, Color.Black),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black,
                        backgroundColor = Color.Transparent
                    ),
                    contentPadding = PaddingValues(15.dp)
                ) {

                    Icon(
                        imageVector = Icons.Filled.Upload,
                        contentDescription = "Upload Media"
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = "Upload Media", fontSize = 18.sp)

                }


                // ACTIONABLE BUTTONS
                Column(
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {

                    Button(
                        onClick = {
                            validateForm(name, description, price)
                            if (validationMessage.isEmpty()) {
                                // try to upload to server
                                // show error message if not successful
                                try {
                                    firestoreViewModel.addTask(
                                        Component(
                                            name = name,
                                            price = price.toDouble(),
                                            description = description
                                        )
                                    )
                                    navigateToHome()
                                } catch (e: Exception) {
                                    coroutineScope.launch {
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            message = e.message!!,
                                            actionLabel = "OK"
                                        )
                                    }
                                }
                            } else {
                                // show snackbar to alert user to fill in all
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
//                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    ) {
                        Text(text = "Add Item", fontSize = 18.sp)
                    }



                    OutlinedButton(
                        onClick = {
                            navigateToHome()
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colors.primary,
                            backgroundColor = Color.White
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
                    ) {
                        Text(text = "Cancel", fontSize = 18.sp)
                    }


                }
            }
        }
    }

}

@Preview
@Composable
fun AddComponentScreenPreview() {
    AddComponentScreen({})
}