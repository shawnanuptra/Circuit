package shawn.martin.circuit.ui.theme

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun customTextFieldColors() : TextFieldColors {
    return TextFieldDefaults.outlinedTextFieldColors(
        backgroundColor = Color(0x26ECECEC),
        textColor = Color.White,
        focusedBorderColor = Color.White,
        unfocusedBorderColor = Color.LightGray,
        focusedLabelColor = Color.White,
        unfocusedLabelColor = Color.LightGray,
        trailingIconColor = Color.LightGray,
    )
}