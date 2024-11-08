package app.mariia.mariia.ui.screens.auth

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

@Composable
fun SignInProviders(
    width: Dp,
    onGoogleSignIn: () -> Unit
) {
    Row(
        modifier = Modifier.width(width),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(Modifier.weight(1F))

        TextButton(
            onClick = { onGoogleSignIn.invoke() },
        ) {
            Text(
                text = "Google",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "or",
            fontSize = 24.sp
        )

        TextButton(
            onClick = { },
        ) {
            Text(
                text = "Email",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}