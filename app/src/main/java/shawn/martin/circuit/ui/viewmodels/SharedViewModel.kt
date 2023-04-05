package shawn.martin.circuit.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import shawn.martin.circuit.data.AuthRepository
import shawn.martin.circuit.data.Resource
import shawn.martin.circuit.model.Component
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val firestore: FirebaseFirestore,
) : ViewModel() {

    var currentUserId = repository.currentUserId;

    // StateFlow of Logging In. Will be used in UI to show Loading, Success, or Failure
    private val _logInFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val logInFlow: StateFlow<Resource<FirebaseUser>?> = _logInFlow

    // StateFlow of Signing up. Will be used in UI to show Loading, Success, or Failure
    private val _signUpFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signUpFlow: StateFlow<Resource<FirebaseUser>?> = _signUpFlow

    // methods to help reset flow states
    fun resetSignUpFlow() {
        _signUpFlow.value = null;
    }

    fun resetLogInFlow() {
        _logInFlow.value = null;
    }

    fun logIn(email: String, password: String) = viewModelScope.launch {
        // Mark as Loading
        _logInFlow.value = Resource.Loading

        // Mark result as Success or Failure when repo.logIn() is finished running
        _logInFlow.value = repository.logIn(email, password)
    }

    fun signUp(email: String, password: String) = viewModelScope.launch {
        // Mark as Loading
        _signUpFlow.value = Resource.Loading

        // Mark result as Success or Failure when repo.signUp() is finished running
        _signUpFlow.value = repository.signUp(email, password)
    }

    fun signOut() {
        // sign out user
        repository.signOut();

        resetSignUpFlow()
        resetLogInFlow()
    }



}

