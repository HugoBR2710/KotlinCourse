package com.example.kotlinbasics
/*
data class CoffeDetails(
    val sugarCount: Int,
    val name: String,
    val size : String,
    val creamAmount : Int
)
*/

fun main() {

    //Immutable list- cannot add items after the initialization
    //val shoppingList = listOf("Processor", "Ram", "Graphics Card", "SSD")
    //Mutable list- can add and modify items

    /*
    val shoppingList = mutableListOf("Processor", "Ram",
        "Graphics Card", "SSD")

    //adding items to lists

    shoppingList.add("Cooling System")
    shoppingList.remove("Graphics Card")
    shoppingList.add("Graphics Card 4090")

    println(shoppingList[2])

    val hasProcessor = shoppingList.contains("Processor")

    println(hasProcessor)

    for(index in 0 until  shoppingList.size) {
        println(shoppingList[index])
    }

     */
}






/*
fun askCoffeDetails() {
    println("Who wants the coffe?")
    var name = readln()
    println("how many sugar spoons?")
    var sugarCount = readln()
    val sugarCountInt = sugarCount.toInt()
}

fun makeCoffe(coffeDetails: CoffeDetails) {
    if(coffeDetails.sugarCount == 0){
        println("1 coffe with no sugar for ${coffeDetails.name}")
    } else if(coffeDetails.sugarCount == 1) {
        println("1 coffe with ${coffeDetails.sugarCount} sugar spoon for ${coffeDetails.name}")
    } else {
        println("1 coffe with ${coffeDetails.sugarCount} sugar spoons for ${coffeDetails.name}")
    }
}
*/
