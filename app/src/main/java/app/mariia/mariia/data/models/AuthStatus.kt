package app.mariia.mariia.data.models

import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

sealed class AuthStatus {
    data object None : AuthStatus()

    data object SignedIn : AuthStatus()

    data class GotException(
        val exception: Exception
    ) : AuthStatus()

    data class GotCredential(
        val credential: GoogleIdTokenCredential
    ) : AuthStatus()
}