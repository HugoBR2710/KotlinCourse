package com.example.rockpaperscissors

fun main() {

    var computerChoice = ""
    var playerChoice = ""

    while(playerChoice != "0") {

        println("Rock, Paper or Scissors? Enter your choice!")
        playerChoice = readln()
        val randomNumber = (1..3).random()

        when (randomNumber) {
            1 -> {
                computerChoice = "Rock"
            }

            2 -> {
                computerChoice = "Paper"

            }

            3 -> {
                computerChoice = "Scissors"
            }
        }

        val winner = when {
            playerChoice == computerChoice -> "Tie"
            playerChoice == "Rock" &&
                    computerChoice == "Scissors" -> "Player"

            playerChoice == "Paper" && computerChoice == "Rock" ->
                "Player"

            playerChoice == "Scissors" && computerChoice == "Paper" ->
                "Player"

            else -> "Computer"
        }

        println("Computer choice: ${computerChoice}")
        println("The winner is: ${winner}")
    }

 }