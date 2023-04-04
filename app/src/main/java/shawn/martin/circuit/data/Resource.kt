package shawn.martin.circuit.data

// A class used to indicate the state of Authentication Flows in the app
sealed class Resource<out R> {
    // use Resource.Success( /data/ ) in Flows when operation is successful
    data class Success<out R>(val result: R) : Resource<R>()
    // use Resource.Failure ( /exception/ ) to catch exceptions in Auth Flows
    data class Failure(val exception: Exception) : Resource<Nothing>()
    // use Resource.Loading to indicate loading state
    object Loading : Resource<Nothing>()

}