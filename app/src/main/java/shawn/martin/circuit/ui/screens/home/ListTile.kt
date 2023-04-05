package shawn.martin.circuit.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Pending
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import shawn.martin.circuit.model.Component

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListTile(
    component: Component,
    navigateToEditComponent: (String) -> Unit
) {
    val name = component.name  ;
    val price = component.price;
    val purchased = component.purchased;

    val purchasedText = if (purchased) "Purchased" else "Not Purchased"

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        contentColor = Color.Black,
        onClick = {
            Log.d("TESTING", component.id)
            navigateToEditComponent(component.id)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Column(Modifier.weight(2f)) {
                Text(text = name, style = MaterialTheme.typography.h6)
                PurchaseRow(purchased = purchased, text = purchasedText)
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight(0.7f)
                    .width(2.dp),
                color = Color.Black
            )
            Text(
                text = "£${String.format("%.2f", price)}",
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.body1.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Divider(color = Color.LightGray, thickness = 1.dp)
}

@Composable
fun PurchaseRow(purchased: Boolean, text : String) {
    if (purchased) {
        Row() {
            Icon(imageVector = Icons.Filled.CheckCircle, contentDescription = "Purchased", tint = Color(0xFF206300))
            Text(text, style = MaterialTheme.typography.subtitle2, color = Color(0xFF206300))
        }
    } else {
        Row() {
            Icon(imageVector = Icons.Filled.Pending, contentDescription = "Not Purchased", tint = Color(0xFFE97000))
            Text(text, style = MaterialTheme.typography.subtitle2, color = Color(0xFFE97000))
        }
    }

}

@Preview
@Composable
fun ListTilePreview() {
}