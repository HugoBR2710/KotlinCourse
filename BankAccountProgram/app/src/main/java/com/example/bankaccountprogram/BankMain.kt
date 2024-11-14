package com.example.bankaccountprogram

fun main(){
    var hugosBankAccount = BankAccount("Hugo Brandão", 2000.00)

    hugosBankAccount.deposit(1000.00)
    hugosBankAccount.withdraw(525.00)
    hugosBankAccount.deposit(2500.00)
    hugosBankAccount.withdraw(100.00)
    hugosBankAccount.displayTransactionHistory()
    hugosBankAccount.displayBalance()
}