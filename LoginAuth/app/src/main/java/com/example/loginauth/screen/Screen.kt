package com.example.loginauth.screen

sealed class Screen(val route:String) {
    object MainScreen: Screen("mainscreen")
    object LoginScreen: Screen("loginscreen")
    object SignupScreen: Screen("signupscreen")
}