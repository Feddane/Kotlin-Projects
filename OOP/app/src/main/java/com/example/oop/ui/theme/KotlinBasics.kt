package com.example.oop.ui.theme

import androidx.compose.ui.text.toLowerCase


open class Car(val name: String, val brand: String) {
    // open so it can be overriden by inhereting classes
    open var range: Double = 0.0
    fun extendRange(amount: Double) {
        if(amount > 0) {
            range += amount
        }
    }
    open fun drive(distance: Double){
        println("Drove for $distance KM")
    }
}

class ElectricCar(name: String, brand: String, batteryLife: Double) : Car(name, brand) {

    var chargerType = "Type1"
    // in KM
    override var range = batteryLife/6

    override fun drive(distance: Double){
        println("Drove for $distance KM on electricity")
    }
}

fun main(){

    var chaïma = person("chaima", "feddane")
    //call the methode in oop
    chaïma.myhobby()

    println("the owner of chaima is ${chaïma.owner} ")


    var user1 = user(1, "mirna khiari")
    var user2 = user(1, "mirna khiari")
    println(user1.equals(user2))
    val user3= user1.copy(name= "maya")
    println(user3)

    /************inheritance***************/
    var audiA3 = Car("A3", "Audi")
    var teslaS = ElectricCar("S-Model", "Tesla", 85.0)

    // Polymorphism is the ability to treat objects
    // with similar traits in a common way
    audiA3.drive(200.0)
    // Only works because ElectricCar is a Subclass of Car
    // Or alternativly works if Car was a Interface and Electriccar would inherit from it
    teslaS.drive(200.0)

}

data class user (val id: Long, var name: String)

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
