package shawn.martin.circuit.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    // sharedViewModel: SharedViewModel
    navigateToWelcome: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Components List") },
                backgroundColor = MaterialTheme.colors.primary,
                actions = {
                    IconButton(onClick = {
                        navigateToWelcome()
                    }) {
                        Icon(imageVector = Icons.Filled.Logout, contentDescription = "Logout")
                    }
                }
            )
        }
    ) { _ ->
        Column() {
            Text(text = "This is welcome screen")
        }
    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen({})
}
