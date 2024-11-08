package app.mariia.mariia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import app.mariia.mariia.ui.screens.auth.Auth
import app.mariia.mariia.ui.theme.MariiaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MariiaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { pv ->
                    Auth(pv)
                }
            }
        }
    }
}