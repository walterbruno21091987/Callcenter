package com.example.callcenter.repository

import com.example.callcenter.entities.Call

object CallRepository {
val calls:MutableList<Call> = mutableListOf()
    init {
        calls.add(Call(1524560324,false,true,"El cliente se enojo bastante"))
    }

   fun callAdd(call:Call):Boolean{
        return calls.add(call)
    }
}