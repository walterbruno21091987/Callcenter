package com.example.callcenter.entities

class Call(val contact:Long, var successful:Boolean,var callAgain:Boolean,val observations:String) {

fun getSuccessful():String{
    var result="SIN EXITO"
    if(successful){
        result="EXITOSA"
    }
    return result
}
fun getCallAgain():String{
    var result="NO"
    if(callAgain){
        result="SI"
    }
    return result
}}