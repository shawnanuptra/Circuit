package shawn.martin.circuit.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import shawn.martin.circuit.data.util.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) : AuthRepository {
    // get currentUserId from Firebase auth
    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null

//    override val currentUser: Flow<User>
//        get() = callbackFlow {
//            val listener =
//                FirebaseAuth.AuthStateListener { auth ->
//                    this.trySend(auth.currentUser?.let { User(it.uid, it.isAnonymous) } ?: User())
//                }
//            auth.addAuthStateListener(listener)
//            awaitClose { auth.removeAuthStateListener(listener) }
//        }

    // logging in user
    override suspend fun logIn(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace() // for debugging
            Resource.Failure(e)
        }
    }

    // signing up user
    override suspend fun signUp(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            Resource.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace() // for debugging
            Resource.Failure(e)
        }
    }

    // signing out user
    override fun signOut() {
        auth.signOut()
    }
}