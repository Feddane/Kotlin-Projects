package com.example.oop.ui.theme

import androidx.compose.ui.text.toLowerCase

fun main(){

    var chaïma = person("chaima", "feddane")
    //call the methode in oop
    chaïma.myhobby()

    println("the owner of chaima is ${chaïma.owner} ")


}

class person (firstName:  String, lastName:  String){

    //constructor
//    constructor(firstName:  String, lastName:  String, age: Int) :this(firstName, lastName){
//            this.age = age
//        }

    lateinit var owner : String

    var crash = "ABEL"

        //getters

//        get() {
//            return field.toLowerCase()
//        }

        get() = field
        //setters
        set(value) {
            field = value
        }

    //methods
    fun myhobby() {
        println("my hobby is $hobby")
    }

    var hobby = "watch netflix"

    init {
        println("person $firstName $lastName created!!")
        this.owner = "mirna"

    }
}
