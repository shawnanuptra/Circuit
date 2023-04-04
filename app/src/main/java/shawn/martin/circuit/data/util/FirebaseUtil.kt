package shawn.martin.circuit.data.util

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

// this function removes the need for callbacks when authenticating with Firebase
// e.g. auth.createUserWithEmailPassword() will return a Task<>. To get the data from this Task<>,
// a function will need to be run inside a CoRoutine. The utility function removes this need
suspend fun <T> Task<T>.await(): T {
    return suspendCancellableCoroutine { continuation ->
        // attach an onComplete listener, to run the code in {} after Coroutine is executed
        addOnCompleteListener {
            // if there is an exception, resume the Coroutine with exception thrown
            if (it.exception != null) {
                continuation.resumeWithException(it.exception!!)
            } else {
                // if there's no exception, resume the Coroutine with value
                continuation.resume(it.result, null)
            }
        }
    }
}