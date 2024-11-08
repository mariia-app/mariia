package app.mariia.mariia.ui.screens.auth

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import app.mariia.mariia.data.models.AuthStatus
import app.mariia.mariia.utils.helpers.checkIfSignedIn
import app.mariia.mariia.utils.helpers.getCredentialResponse
import app.mariia.mariia.utils.helpers.getFirebaseResponse

class AuthViewModel(private val auth: FirebaseAuth) : ViewModel() {
    private val _authStatus = mutableStateOf<AuthStatus>(AuthStatus.None)
    val authStatus: State<AuthStatus> = _authStatus

    init {
        if (checkIfSignedIn(auth)) {
            _authStatus.value = AuthStatus.SignedIn
        }
    }

    fun signInWithGoogle(ctx: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            async {
                _authStatus.value = getCredentialResponse(ctx)
            }.await()

            if (authStatus.value is AuthStatus.GotCredential) {
                val credential = (authStatus.value as AuthStatus.GotCredential).credential
                _authStatus.value = getFirebaseResponse(auth = auth, credential = credential)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}