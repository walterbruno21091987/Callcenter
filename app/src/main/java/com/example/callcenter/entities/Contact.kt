package com.example.callcenter.entities

import android.graphics.Color

class Contact(var nameAndSurname:String,var phone:Long,var email:String,var adress:String, var postalCode:Int,var location:String,var provincia:Provincia,var isClient:Boolean=false,var callAgain:Boolean=true) {
    fun getIsClient():String{
        var result="no"
        if(isClient==true){
            result="si"
        }
        return result
    }
    fun getCallAgain():String{
        var result="no"
        if(isClient==true){
            result="si"
        }
        return result
    }
    companion object{
        fun validateEmail(emailEntered: String):Boolean{
            var result=false
            var arroba=false
            var punto=false


            if  (!emailEntered.none { it=='.' }){
                punto=true
            }
            if(!emailEntered.none { it=='@' })
                arroba=true
            if(arroba&&punto){
                result=true
            }
            return result
        }
}}