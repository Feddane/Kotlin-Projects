package com.example.kotlinfundamentals.ui.theme

fun main(){
    val fruits = setOf("apple", "mango", "orange")
    println(fruits.size)

    val new = fruits.toMutableList()
    new.add("water mellon")
    print(new)

    val daysofweek =  mapOf(1 to "monday", 2 to "sunday")
    println(daysofweek[2])

    for (key in daysofweek.keys){
        println("$key is to ${daysofweek[key]}")
    }

    println(daysofweek.toSortedMap())
}