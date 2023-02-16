package com.example.callcenter.repository

object CoverageArea{
   private val areas:MutableSet<Int> = mutableSetOf()
    init {
        areas.add(1778)
        areas.add(6588)
    }
    fun addArea(code:Int):Boolean{
        return areas.add(code)
    }
    fun getArea(code:Int):Boolean{
        return !areas.none { it.equals(code) }
    }
}