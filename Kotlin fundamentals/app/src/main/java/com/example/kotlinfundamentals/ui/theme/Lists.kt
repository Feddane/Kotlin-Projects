package com.example.kotlinfundamentals.ui.theme

fun main(){

    val months = listOf("january", "february", "march", "april")
    val anytypes = listOf("january", 1, 2, 3, true)
    println(anytypes.size)

    for(month in months){
        println(month)
    }

    val additionalmonths = months.toMutableList()
    val newmonths = arrayOf("may", "june")
    additionalmonths.addAll(newmonths)
    print(additionalmonths)

}



