package com.example.kotlinfundamentals

fun main(){

    //you can create a var without writing the type

    val she = "mirna" //val can't be overwritten

    var me = "chaïma" //var we can change it
    me = "sonia"
    println("hello $me!")

    //integer types: Bytes(8 bit), short(16 bit), long(64 bit), int(32 bit)
    var myByte: Byte = 13
    var myInt: Int = 13254698
    var myLong: Long = 1323697456048
    var myShort: Short = 132

    //floating number types: Float(32bit), Double(64bit)
    val myFloat : Float = 16.27F
    val myDouble : Double = 16.45478764879999845

    //Boolean type
    var isSunny: Boolean = false
    isSunny = true

    //char and string type
    var stringWord: String = "hello"
    var length = stringWord.length

    println("the length of this word is $length")     //string templates $
    var letterchar = "h"

    //arithmetic operators (+, -, *,/, %)
    var res = 4 + 6
    //res /= 2
    //res *= 2
    //res -= 2
    res %= 2
    print("the result is: $res  ")


    //comparaison arithmetics (==, !=, <, >,<=, >=)
    val isEqual = 5==3
    val isDifferent = 5!=3
    println(isEqual)
    println("is5greater3 ${5>3}   ")
    println("${5<=3}   ")

    //Assignment operators (+=, -=, *=, /=, %=)
    var myNum = 5
    myNum +=3
    myNum *=3

    //increment & decrement operators(++, --)
    myNum++
    myNum--

    //if statements
    var heightPerson1 = 170
    var heightPerson2 = 180

    if (heightPerson1 < heightPerson2){
        println("yes")
    }else if(heightPerson1 > 192){
        println("well")
    }else{
        println("no")
    }

    var name = "chaima"
    if(name == "chaima" ){
        println("love your name!!")
    }

    //when expressions
    var month = 3
    when (month){
        in 3..5 -> println("spring")
        in 6..8  -> println("summer")
        in 9..11-> println("fall")
        12, 1,2 -> println("fall")
        else -> println("Invalid season")
    }

    var x : Any = 13.25
    when(x){
        is Int -> println("$x is an int")
        is Double -> println("$x is a double")
        is Float -> println("$x is a float")
        else -> println("no one!!")
    }


}