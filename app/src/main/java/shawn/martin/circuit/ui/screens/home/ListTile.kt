package shawn.martin.circuit.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import shawn.martin.circuit.model.Component

@Composable
fun ListTile(
    component : Component
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
                Text(text = purchasedText, style = MaterialTheme.typography.subtitle2)
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

@Preview
@Composable
fun ListTilePreview() {
}