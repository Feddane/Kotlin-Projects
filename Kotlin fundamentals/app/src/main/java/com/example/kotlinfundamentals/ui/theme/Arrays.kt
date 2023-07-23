package com.example.kotlinfundamentals.ui.theme

fun main(){
    val numbers = arrayOf(1,2,3,4,5,6)
//    println(numbers.contentToString())

//    for (element in  numbers){
//        println(element)
//    }

//    println(numbers[0])

    val fruit = arrayOf(Fruit("apple", 2.5), Fruit("orange", 3.0))
    print(fruit.contentToString())

}

data class Fruit (val name: String, val price: Double)