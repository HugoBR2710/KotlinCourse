package com.example.bankaccountprogram

class BankAccount(
    var accountHolder: String,
    var balance: Double) {

    private val transactionHistory = mutableListOf<String>()

    fun deposit(amount: Double){
        balance += amount
        transactionHistory.add("$accountHolder deposited $amount€")
    }

    fun withdraw(amount: Double){
        if(balance <= amount){
            println("You don't have $amount€ to withdraw")
        }else {
            balance -= amount
            transactionHistory.add("$accountHolder withdred $amount€")
        }

    }

    fun displayBalance(){
        println("$balance€")
    }

    fun displayTransactionHistory(){

        println("Transaction history for $accountHolder")
        for(transaction in transactionHistory){
            println(transaction)
        }
    }
}