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
    protected open var  c = 10
    public var g = 5
}

//nested class is such a class which is created inside another class
//it is by default static, their data can be accessed without creating an object of the class
class outerClass{

    class NestedClass{}

}


//inner class is a class which is created inside another class with keyword inner
//it can not be declared inside interfaces or non-inner nested classes
//it is able to access members of its outer class even if it is private
class outerClass2{

   inner class innerclass{}

}



//unsafe cast operator: as
//a nullable string (string?) cannot be cast to non nullable string (string) this throws an excaption


//safe cast operator: as?
//it returns anull if casting is not possible rather than throwig an ClassCastException exception

//try and catch
//*************1-throw MyException ("this is throws an exception")
//*************2- try, catch, finally, throw
//try{
//
//}catch(e: someexception){
//
//}finally{
//
//}

