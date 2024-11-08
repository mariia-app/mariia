package app.mariia.mariia.ui.screens.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.mariia.mariia.R
import app.mariia.mariia.data.models.AuthStatus
import app.mariia.mariia.ui.composables.logo.LargeLogo
import org.koin.androidx.compose.koinViewModel

@SuppressLint("RememberReturnType")
@Composable
fun Auth(innerPadding: PaddingValues) {
    val ctx = LocalContext.current
    val density = LocalDensity.current

    val vm = koinViewModel<AuthViewModel>()

    val authStatus by remember { vm.authStatus }
    val isException by remember { derivedStateOf { authStatus is AuthStatus.GotException } }
    val errorMessage by remember {
        derivedStateOf {
            if (isException) (authStatus as AuthStatus.GotException).exception.message ?: "" else ""
        }
    }
    val width = remember { mutableStateOf(0.dp) }

    val onGoogleSignIn = remember {
        { vm.signInWithGoogle(ctx) }
    }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(paddingValues = innerPadding)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LargeLogo()

            Spacer(
                modifier = Modifier
                    .height(96.dp)
            )

            Row {
                Spacer(Modifier.weight(1F))

                Column {
                    Text(
                        modifier = Modifier
                            .onSizeChanged {
                                with(density) {
                                    width.value = it.width.toDp()
                                }
                            },
                        text = ctx.getString(R.string.greeting),
                        fontSize = 24.sp,
                    )

                    SignInProviders(
                        width = width.value,
                        onGoogleSignIn = onGoogleSignIn
                    )
                }

                Spacer(Modifier.width(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun AuthPreview() {
    Auth(PaddingValues())
}
