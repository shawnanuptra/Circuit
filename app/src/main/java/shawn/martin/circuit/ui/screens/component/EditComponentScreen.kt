package shawn.martin.circuit.ui.screens.component

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import shawn.martin.circuit.ui.theme.CircuitTheme
import shawn.martin.circuit.ui.viewmodels.FirestoreViewModel
import shawn.martin.circuit.util.Constants.SCREEN_HORIZONTAL_PADDING

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditComponentScreen(
    navigateToHome: () -> Unit,
    componentId: String,
//    sharedViewModel: SharedViewModel = hiltViewModel(),
    firestoreViewModel: FirestoreViewModel = hiltViewModel(),
) {

//     use component value, that is stored in firestoreVM
//     the value is a mutable(), hence 'by'
    var component by firestoreViewModel.component

//     get component value from firestore before init of page itself
    LaunchedEffect(Unit) { component = firestoreViewModel.getComponent(componentId)!! }

    val scaffoldState = rememberScaffoldState();
    val coroutineScope = rememberCoroutineScope();
    CircuitTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = { Text(text = component.name) },
                    backgroundColor = MaterialTheme.colors.primary,
                    navigationIcon = {
                        IconButton(onClick = {
                            navigateToHome()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Cancel"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            firestoreViewModel.updateTask(component)
                            navigateToHome()
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
                        value = component.name,
                        onValueChange = { firestoreViewModel.onNameChange(it) },
                        singleLine = true,
                        placeholder = { Text(text = "e.g. HyperX DDR4 16GB RAM") },
                    )
                }

                // DESCRIPTION FIELD
                Column() {
                    Text("Description", style = MaterialTheme.typography.h6)
                    OutlinedTextField(
                        value = component.description,
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = { firestoreViewModel.onDescriptionChange(it) },
                        placeholder = { Text(text = "e.g. HyperX DDR4 16GB RAM") },
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
                            //  colors = customTextFieldColors(),
                            value = component.price.toString(),
                            onValueChange = { firestoreViewModel.onPriceChange(it.toDouble()) },
//                        label = { Text("Component") },
                            placeholder = { Text(text = "e.g. HyperX DDR4 16GB RAM") },
                        )
                    }

                }

                // Black Buttons
                Column(
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    // ROW
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Share Button
                        OutlinedButton(
                            onClick = {},
                            border = BorderStroke(1.dp, Color.Black),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                backgroundColor = Color.Transparent
                            ),
                            contentPadding = PaddingValues(10.dp)
                        ) {
                            Icon(imageVector = Icons.Filled.Share, contentDescription = "Share")
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(text = "Share")
                        }


                        // Purchased Button
                        OutlinedButton(
                            onClick = {
                                      firestoreViewModel.onPurchasedChange(!component.purchased)
                            },
                            border = BorderStroke(
                                1.dp,
                                if (component.purchased) Color(0xFF206300) else Color(0xFFE97000)
                            ),
                            colors =
                            if (component.purchased) {
                                ButtonDefaults.buttonColors(
                                    contentColor = Color(0xFF206300),
                                    backgroundColor = Color(0x0D206300)
                                )
                            } else {
                                ButtonDefaults.buttonColors(
                                    contentColor = Color(0xFFE97000),
                                    backgroundColor = Color(0x0DE97000)
                                )
                            },
                            contentPadding = PaddingValues(10.dp)
                        ) {
                            if (component.purchased) {
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = "Purchased"
                                )
                                Spacer(modifier = Modifier.size(5.dp))
                                Text("Purchased")
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.Pending,
                                    contentDescription = "Not Purchased"
                                )
                                Spacer(modifier = Modifier.size(5.dp))
                                Text("Not Purchased")
                            }

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
                }


                // ACTIONABLE BUTTONS
                Column(
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {

                    Button(
                        onClick = {
                            // update component
                            try {
                                firestoreViewModel.updateTask(component)
                                navigateToHome()
                            } catch (e: Exception) {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Operation failed, please try again later",
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
                        Text(text = "Update Item", fontSize = 18.sp)
                    }

                    OutlinedButton(
                        onClick = {
                            try {
                                // delete
                                firestoreViewModel.deleteTask(component)
                                navigateToHome()
                            } catch (e: Exception) {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Operation failed, please try again later",
                                        actionLabel = "OK"
                                    )
                                }
                            }
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color(0xFFFF4343),
                            backgroundColor = Color(0x29FF5151)
                        ),
                        border = BorderStroke(1.dp, Color.Red)
                    ) {
                        Text(text = "Delete", fontSize = 18.sp)
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
fun ComponentScreenPreview() {
}