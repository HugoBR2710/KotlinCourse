package com.example.loginauth.screen

import AuthViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginauth.data.Result

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(

    onNavigateToLogin: () -> Unit,
    authViewModel: AuthViewModel

) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("")}
    var lastName by remember { mutableStateOf("") }

    val result by authViewModel.authResult.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Sign Up!",
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge)
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text("Email")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
        OutlinedTextField(
            value =password,
            onValueChange = {password = it},
            label = { Text("Password")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = firstName,
            onValueChange = {firstName = it},
            label = { Text("First Name")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
        OutlinedTextField(
            value = lastName,
            onValueChange = {lastName = it},
            label = { Text("Last Name")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
        //Logic to handle signup button
        Button(
            onClick = {authViewModel.signUp(email, password, firstName, lastName)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Sign up")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Already have an account? Sign in.",
            modifier = Modifier.clickable { onNavigateToLogin() })

        // Verificando o resultado do signUp
        when (val authResult = result) {
            is Result.Success -> {
                // Navega para a tela de login ou outra página
                onNavigateToLogin()
            }
            is Result.Error -> {
                // Exibe erro se houver
                Text("Error: ${authResult.exception.message}")
            }
            else -> {}
        }
    }
}

@Preview (showBackground = true)
@Composable
fun SignUpPreview() {
    //SignUpScreen({})
}