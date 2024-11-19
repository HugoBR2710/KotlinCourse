package ipca.pdm.myshoppinglist

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

data class RegisterState(
    var username: String = "",
    var password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class RegisterViewModel : ViewModel() {

    var state = mutableStateOf(RegisterState())
        private set

    private val username
        get() = state.value.username
    private val password
        get() = state.value.password

    fun onUsernameChange(newValue: String) {
        state.value = state.value.copy(username = newValue)
    }

    fun onPasswordChange(newValue: String) {
        state.value = state.value.copy(password = newValue)
    }

    fun register( onRegisterSuccess: () -> Unit) {

        if (username.isEmpty()) {
            state.value = state.value.copy(error = "Email is required")
            return
        }
        if (password.isEmpty()) {
            state.value = state.value.copy(error = "Password is required")
            return
        }

        state.value = state.value.copy(isLoading = true)
        val auth = Firebase.auth
        auth.createUserWithEmailAndPassword(state.value.username, state.value.password)
            .addOnCompleteListener { task ->
                state.value = state.value.copy(isLoading = false)
                if (task.isSuccessful) {
                    // Register success, update UI with the signed-in user's information
                    Log.d(TAG, "registerWithEmail:success")
                    val user = auth.currentUser
                    state.value = state.value.copy(error = null)
                    onRegisterSuccess()
                } else {
                    // If register fails, display a message to the user.
                    Log.w(TAG, "registerWithEmail:failure", task.exception)
                    state.value = state.value.copy(error = task.exception.toString())
                }
            }
    }

}