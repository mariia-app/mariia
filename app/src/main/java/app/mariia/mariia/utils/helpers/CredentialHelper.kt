package app.mariia.mariia.utils.helpers

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import app.mariia.mariia.data.models.AuthStatus
import app.mariia.mariia.utils.WEB_CLIENT_ID

suspend fun getCredentialResponse(
    ctx: Context
): AuthStatus {
    val credentialManager = CredentialManager.create(ctx)

    val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false)
        .setServerClientId(WEB_CLIENT_ID)
        .setAutoSelectEnabled(false)
        .build()

    val request: GetCredentialRequest = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()

    return try {
        val response = credentialManager.getCredential(context = ctx, request = request)
        val credential = response.credential
        val idToken = GoogleIdTokenCredential.createFrom(credential.data)
        AuthStatus.GotCredential(credential = idToken)
    } catch (e: Exception) {
        AuthStatus.GotException(exception = e)
    }
}