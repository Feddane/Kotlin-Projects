package com.example.oop.ui.theme

fun main(){

    var chaïma = person("chaima", "feddane")
    //call the methode in oop
    chaïma.myhobby()

}

class person (firstName:  String, lastName:  String){

    //constructor
    constructor(firstName:  String, lastName:  String, age: Int) :this(firstName, lastName){
            this.age = age
        }


    //methods
    fun myhobby() {
        println("my hobby is $hobby")
    }

    var hobby = "watch netflix"

    init {
        println("person $firstName $lastName created!!")


    }
}
