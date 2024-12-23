package ipca.pdm.myshoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import ipca.pdm.myshoppinglist.ui.theme.MyShoppingListTheme

const val TAG = "myshoppinglist"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var navController = rememberNavController()

            MyShoppingListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var auth = Firebase.auth
                    if(auth.currentUser == null) {
                        NavHost(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController,
                            startDestination = "login") {
                            composable("login") {
                                LoginView(
                                    onNavigateToRegister = {navController.navigate("register")},
                                    onNavigateToMain = {navController.navigate("home")}
                                )
                            }
                            composable("register") {
                                RegisterView(
                                    onNavigateToLogin = {navController.navigate("login")},
                                    onNavigateToMain = {navController.navigate("home")}
                                )
                            }
                            composable("home") {
                                AddListTypesView()
                            }
                        }
                    } else if(auth.currentUser != null){
                        NavHost(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController,
                            startDestination = "home") {
                            composable("login") {
                                LoginView(
                                    onNavigateToRegister = {navController.navigate("register")},
                                    onNavigateToMain = {navController.navigate("home")}
                                )
                            }
                            composable("register") {
                                RegisterView(
                                    onNavigateToLogin = {navController.navigate("login")},
                                    onNavigateToMain = {navController.navigate("home")}
                                )
                            }
                            composable("home") {
                                AddListTypesView()
                            }
                        }
                    }

                }
            }

            LaunchedEffect(Unit) {

                val auth = Firebase.auth

                val currentUser = auth.currentUser
                if (currentUser != null) {
                    navController.navigate("home")
                }
            }
        }
    }
}
