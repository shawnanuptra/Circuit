package shawn.martin.circuit.ui.screens.component

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shawn.martin.circuit.model.Component
import shawn.martin.circuit.ui.theme.CircuitTheme
import shawn.martin.circuit.util.Constants.SCREEN_HORIZONTAL_PADDING

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ComponentScreen(
    component: Component,
    navigateToWelcome: () -> Unit,
//    sharedViewModel: SharedViewModel = hiltViewModel(),
//    firestoreViewModel: FirestoreViewModel = hiltViewModel(),
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf(null) }


    CircuitTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = component.name) },
                    backgroundColor = MaterialTheme.colors.primary,
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
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
                        value = name,
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = { name = it },
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
                            value = name,
                            onValueChange = { name = it },
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
                            Text("Purchased")

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
//                    navigateToSignup()
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
//                    navigateToSignup()
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
    ComponentScreen(
        Component(
            "id34",
            "Test name",
            "this is description",
            30.00,
            true
        )
    ) {

    }
}