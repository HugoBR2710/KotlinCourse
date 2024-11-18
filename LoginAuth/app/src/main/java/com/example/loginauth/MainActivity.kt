package com.example.loginauth

import AuthViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginauth.data.UserRepository
import com.example.loginauth.screen.Screen
import com.example.loginauth.screen.SignUpScreen
import com.example.loginauth.screen.loginScreen
import com.example.loginauth.ui.theme.LoginAuthTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.example.loginauth.screen.MainScreen
import com.example.loginauth.viewmodel.AuthViewModelFactory

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()

        setContent {

            // Verificando se o usuário já está autenticado
            val auth = FirebaseAuth.getInstance()

            // Cria a instância do UserRepository
            val userRepository = UserRepository(auth)

            // Cria a ViewModel usando o AuthViewModelFactory
            val authViewModel: AuthViewModel = viewModel(
                factory = AuthViewModelFactory(userRepository)
            )

            val navController = rememberNavController()


            val startDestination = if (auth.currentUser != null) Screen.MainScreen.route else Screen.SignupScreen.route

            LoginAuthTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavigationGraph(navController = navController, startDestination = startDestination, authViewModel = authViewModel)
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String,
    authViewModel: AuthViewModel
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(Screen.SignupScreen.route) {
            SignUpScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = { navController.navigate(Screen.LoginScreen.route) }
            )
        }
        composable(Screen.LoginScreen.route) {
            loginScreen(
                authViewModel = authViewModel,
                onNavigateToSignUp = { navController.navigate(Screen.SignupScreen.route)},
                onNavigateToMain = {navController.navigate(Screen.MainScreen.route)})
        }
        composable(Screen.MainScreen.route) {
            // Verifique se o utilizador está autenticado antes de renderizar a tela principal
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null) {
                MainScreen(onNavigateToMain = {navController.navigate(Screen.MainScreen.route)})
            } else {
                navController.navigate(Screen.LoginScreen.route) // Redirecionar para login se não autenticado
            }
        }
    }
}
