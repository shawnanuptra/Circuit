package shawn.martin.circuit.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import shawn.martin.circuit.ui.viewmodels.SharedViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navigateToWelcome: () -> Unit,
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
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
        }
    ) { _ ->
        Column() {
            Text(text = sharedViewModel.currentUserId)
        }
    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen({})
}
