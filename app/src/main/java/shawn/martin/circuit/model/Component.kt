package shawn.martin.circuit.model

import com.google.firebase.firestore.DocumentId

data class Component(
    @DocumentId val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: Double = 0.00,
    val purchased: Boolean = false,
)
