package com.example.kotlinfundamentals.ui.theme

fun main(){

    val arraylist: ArrayList<String> = ArrayList<String>()
    arraylist.add("one")
    arraylist.add("two")
    arraylist.add("three")

    for (i in arraylist){
        print(i+",")
    }


    //lambda expression: is a function which has no namae.
    val sum: (Int, Int) -> Int = {a:Int, b:Int -> a+ b}
    println(sum(10,5))


    //or
    val sum2 = {a:Int, b:Int -> a+ b}
    println(sum(10,5))

}


//visibility modifiers

class base(){
    var a = 1
    private var b = 2
    protected open var  c = 4
    public var g = 5
}
