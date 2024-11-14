package com.example.corefunctions

class Offsprings : Secondary(), Archery, Singer {
    override fun Archer() {
        super.Archer()
        println("Archery skills enhanced by offspring")
    }

    override fun Sing() {
        super.Sing()
        println("Singing skills enhanced by offspring")
    }
}