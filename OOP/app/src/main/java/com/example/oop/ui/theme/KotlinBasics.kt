package com.example.oop.ui.theme

import androidx.compose.ui.text.toLowerCase

/********************inheritance*********************/
//open class Car(val name: String, val brand: String) {
//    // open so it can be overriden by inhereting classes
//    open var range: Double = 0.0
//    fun extendRange(amount: Double) {
//        if(amount > 0) {
//            range += amount
//        }
//    }
//    open fun drive(distance: Double){
//        println("Drove for $distance KM")
//    }
//}
//
//class ElectricCar(name: String, brand: String, batteryLife: Double) : Car(name, brand) {
//
//    var chargerType = "Type1"
//    // in KM
//    override var range = batteryLife/6
//
//    override fun drive(distance: Double){
//        println("Drove for $distance KM on electricity")
//    }
//}


/************************interfaces***************************/
//interface Driveable {
//    val maxSpeed: Double
//    fun drive(): String
//    fun brake(){
//        println("The drivable is braking")
//    }
//}
//
//// Class Car which extends the interface
//open class Car(override val maxSpeed: Double,
//               open val brandName: String
//) : Driveable {
//    // open so it can be overriden by inhereting classes
//    open var range: Double = 0.0
//
//    open fun extendRange(amount: Double) {
//        if (amount > 0) {
//            range += amount
//        }
//
//    }
//
//    override fun drive(): String {
//        println("Drove for $range KM")
//        return range.toString()
//    }
//
//    // overridden functions are implicitly open:
//    override fun brake() {
//        println("The car is breaking")
//    }
//}
//// In case there is no primary Constructor
//class ElectricCar(override val maxSpeed: Double,
//                  override val brandName: String,
//                  batteryLife: Double) : Car(maxSpeed, brandName) {
//
//    // in KM
//    override var range = batteryLife/6
//
//    override fun drive() = "Overriding the drive of my Car"
//
//    fun drive(distance: Double){
//        println("Drove for $distance KM on electricity")
//    }
//    override fun brake(){
//        println("The electirc car is breaking")
//    }
//}

/*************** Abstractt************************/
abstract class Mammal(val name: String, val origin: String,
                      val weight: Double) {   // Concrete (Non Abstract) Properties

    // Abstract Property (Must be overridden by Subclasses)
    abstract var maxSpeed: Double

    // Abstract Methods (Must be implemented by Subclasses)
    abstract fun run()
    abstract fun breath()

    // Concrete (Non Abstract) Method
    fun displayDetails() {
        println("Name: $name, Origin: $origin, Weight: $weight, " +
                "Max Speed: $maxSpeed")
    }
}

class Human(name: String, origin: String, weight: Double,
            override var maxSpeed: Double): Mammal(name, origin, weight) {

    override fun run() {
        // Code to run
        println("Runs on two legs")
    }

    override fun breath() {
        // Code to breath
        println("Breath through mouth or nose")
    }
}

class Elephant(name: String, origin: String, weight: Double,
               override var maxSpeed: Double): Mammal(name, origin, weight) {

    override fun run() {
        // Code to run
        println("Runs on four legs")
    }

    override fun breath() {
        // Code to breath
        println("Breath through the trunk")
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
//    var audiA3 = Car("A3", "Audi")
//    var teslaS = ElectricCar("S-Model", "Tesla", 85.0)
//
//    // Polymorphism is the ability to treat objects
//    // with similar traits in a common way
//    audiA3.drive(200.0)
//    // Only works because ElectricCar is a Subclass of Car
//    // Or alternativly works if Car was a Interface and Electriccar would inherit from it
//    teslaS.drive(200.0)


    /**********interfaces*******************/
//    var audiA3 = Car(200.0, "Audi")
//    var teslaS = ElectricCar(250.0, "Tesla", 85.0)
//
//    // Polymorphism is the ability to treat objects
//    // with similar traits in a common way
//    audiA3.drive()
//    // Only works because ElectricCar is a Subclass of Car
//    // Or alternatively works if Car was an Interface and ElectricCar would inherit from it
//    teslaS.drive()
//    teslaS.drive(200.0)


    /************Abstract*******************/
    val human = Human("Denis", "Russia",
        70.0, 28.0)
    val elephant = Elephant("Rosy", "India",
        5400.0, 25.0)

    human.run()
    elephant.run()

    human.breath()
    elephant.breath()
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



    /*****typecasting*********/
    val obj : Any= 1333
    val str : String? =  obj as? String


}
