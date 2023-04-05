package shawn.martin.circuit.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import shawn.martin.circuit.ui.viewmodels.FirestoreViewModel
import shawn.martin.circuit.ui.viewmodels.SharedViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navigateToWelcome: () -> Unit,
    navigateToAddComponent: () -> Unit,
    sharedViewModel: SharedViewModel = hiltViewModel(),
    firestoreViewModel: FirestoreViewModel = hiltViewModel(),
) {
    val allComponents = firestoreViewModel.allComponents.collectAsState(listOf())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Components List") },
                backgroundColor = MaterialTheme.colors.primary,
                actions = {
                    IconButton(onClick = {
                        sharedViewModel.signOut()
                        navigateToWelcome()
                    }) {
                        Icon(imageVector = Icons.Filled.Logout, contentDescription = "Logout")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToAddComponent() },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "New Component")
            }
        }
    ) { _ ->
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                items = allComponents.value, itemContent = { item ->
                    ListTile(item)
                }
            )
        }
        if (allComponents.value.isEmpty()) {
            Text(text = "This is empty!!")
        }
    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen({}, {})
}
