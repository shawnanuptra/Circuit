package shawn.martin.circuit.data

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUserId: String
    val hasUser: Boolean

//    val currentUser: Flow<User>

    suspend fun logIn(email: String, password: String) : Resource<FirebaseUser>
    suspend fun signUp(email: String, password: String) : Resource<FirebaseUser>
    fun signOut()
}
