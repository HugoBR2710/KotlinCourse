import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.loginauth.data.UserRepository
import com.example.loginauth.data.Result
import kotlinx.coroutines.launch

class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> get() = _authResult

    // Função de registro
    fun signUp(email: String, password: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            // Realiza o registro de usuário e, após sucesso, armazena dados extras no Firestore
            _authResult.value = userRepository.registerUser(email, password, firstName, lastName)
        }
    }

    // Função de login
    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.loginUser(email, password)
        }
    }
}

