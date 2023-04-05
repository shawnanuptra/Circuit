package shawn.martin.circuit.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import shawn.martin.circuit.data.AuthRepository
import shawn.martin.circuit.model.Component
import javax.inject.Inject

@HiltViewModel
class FirestoreViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val repository: AuthRepository,
) : ViewModel() {
    // FIRESTORE FUNCTIONS
    private val _componentsFlow = MutableStateFlow<List<Component?>>(listOf(null))
    val componentsFlow: StateFlow<List<Component?>> = _componentsFlow

    val component: MutableState<Component> = mutableStateOf(Component())

    var allComponents: Flow<List<Component>> =
        firestore.collection(USERS_COLLECTION).document(repository.currentUserId)
            .collection(COMPONENTS_COLLECTION)
            .snapshots().map { snapshot ->
                snapshot.toObjects()
            }

    fun addTask(component: Component) {
        firestore.collection(USERS_COLLECTION).document(repository.currentUserId)
            .collection(COMPONENTS_COLLECTION).add(component)
    }

    fun updateTask(component: Component) {
        firestore.collection(USERS_COLLECTION).document(repository.currentUserId)
            .collection(COMPONENTS_COLLECTION).document(component.id).set(component)
    }

    fun deleteTask(component: Component) {
        firestore.collection(USERS_COLLECTION).document(repository.currentUserId)
            .collection(COMPONENTS_COLLECTION).document(component.id).delete()
    }


    suspend fun getComponent(componentId: String): Component? =
        firestore.collection(USERS_COLLECTION).document(repository.currentUserId)
            .collection(COMPONENTS_COLLECTION).document(componentId).get().await()
            .toObject<Component>()

    // for changes in the EditComponentScreen
    fun onNameChange(newName: String) {
        component.value = component.value.copy(name = newName)
    }

    fun onDescriptionChange(newDesc: String) {
        component.value = component.value.copy(description = newDesc)
    }

    fun onPurchasedChange(newPurchased: Boolean) {
        component.value = component.value.copy(purchased = newPurchased)
    }

    fun onPriceChange(newPrice: Double) {
        component.value = component.value.copy(price = newPrice)
    }


    companion object {
        val USERS_COLLECTION = "users"
        val COMPONENTS_COLLECTION = "components"

    }


}