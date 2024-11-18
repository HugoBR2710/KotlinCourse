package com.example.loginauth.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.tasks.await



class UserRepository(private val auth: FirebaseAuth) {

    private val firestore = FirebaseFirestore.getInstance()

    // Função para registrar usuário
    suspend fun registerUser(email: String, password: String, firstName: String, lastName: String): Result<Boolean> {
        return try {
            // Cria o utilizador com e-mail e senha no Firebase Authentication
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()

            // Após criar o utilizador, salva os dados adicionais no Firestore
            saveUserToFirestore(email, firstName, lastName)

            Result.Success(true)  // Cadastro bem-sucedido
        } catch (e: Exception) {
            Result.Error(e)  // Caso ocorra um erro
        }
    }

    // Função para salvar os dados no Firestore
    private suspend fun saveUserToFirestore(email: String, firstName: String, lastName: String) {
        val user = hashMapOf(
            "firstName" to firstName,
            "lastName" to lastName,
            "email" to email
        )

        firestore.collection("users")
            .document(email) // Usando o email como ID do documento
            .set(user)
            .await()
    }

    // Função de login
    suspend fun loginUser(email: String, password: String): Result<Boolean> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.Success(true) // Login bem-sucedido
        } catch (e: Exception) {
            Result.Error(e) // Caso haja erro
        }
    }
}


