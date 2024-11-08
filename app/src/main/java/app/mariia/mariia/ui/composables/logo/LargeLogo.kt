package app.mariia.mariia.ui.composables.logo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LargeLogo() {
    Row {
        Spacer(Modifier.width(16.dp))

        Column {
            Column {
                Text(
                    text = "Merci for being!",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "with us.",
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(Modifier.weight(1F))
    }
}