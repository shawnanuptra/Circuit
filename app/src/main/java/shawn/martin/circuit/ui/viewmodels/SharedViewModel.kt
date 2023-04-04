package shawn.martin.circuit.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import shawn.martin.circuit.data.AuthRepository
import shawn.martin.circuit.data.Resource
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    // StateFlow of Logging In. Will be used in UI to show Loading, Success, or Failure
    private val _logInFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _logInFlow

    // StateFlow of Signing up. Will be used in UI to show Loading, Success, or Failure
    private val _signUpFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signUpFlow: StateFlow<Resource<FirebaseUser>?> = _signUpFlow


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

        // reset logIn & signUp StateFlow values
        _signUpFlow.value = null;
        _logInFlow.value = null;
    }

}