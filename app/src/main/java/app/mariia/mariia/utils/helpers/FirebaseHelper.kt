package app.mariia.mariia.utils.helpers

import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import app.mariia.mariia.data.models.AuthStatus
import kotlinx.coroutines.tasks.await

suspend fun getFirebaseResponse(
    auth: FirebaseAuth,
    credential: GoogleIdTokenCredential
): AuthStatus {
    val firebaseCredential = GoogleAuthProvider.getCredential(credential.idToken, null)
    var response: AuthStatus = AuthStatus.None

    firebaseCredential.let {
        auth.signInWithCredential(firebaseCredential)
            .addOnSuccessListener {
                response = AuthStatus.SignedIn
            }
            .addOnFailureListener { e ->
                response = AuthStatus.GotException(exception = e)
            }
            .await()
    }

    return response
}

suspend fun signUpWithEmail(
    auth: FirebaseAuth,
    email: String,
    password: String
): AuthStatus {
    var response: AuthStatus = AuthStatus.None

    try {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                response = AuthStatus.SignedIn
            }
            .addOnFailureListener { e ->
                response = AuthStatus.GotException(exception = e)
            }
            .await()
    } catch (e: Exception) {
        response = AuthStatus.GotException(e)
    }

    return response
}

suspend fun signInWithEmail(
    auth: FirebaseAuth,
    email: String,
    password: String
): AuthStatus {
    var response: AuthStatus = AuthStatus.None

    try {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                response = AuthStatus.SignedIn
            }
            .addOnFailureListener { e ->
                response = AuthStatus.GotException(exception = e)
            }
            .await()
    } catch (e: Exception) {
        response = AuthStatus.GotException(e)
    }

    return response
}

fun checkIfSignedIn(auth: FirebaseAuth): Boolean {
    val user = auth.currentUser
    return user != null
}

fun signOut(auth: FirebaseAuth) {
    auth.signOut()
}