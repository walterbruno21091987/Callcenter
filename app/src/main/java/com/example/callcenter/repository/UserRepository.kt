package com.example.callcenter.repository

import com.example.callcenter.entities.ColorFavorit
import com.example.callcenter.entities.User

object UserRepository {
    private var users:MutableSet<User> = mutableSetOf()
  init{
      users.add(User("ratawalter","Wabi2109","walter","bruno", ColorFavorit.AZUL))
  }
    fun addUser(user: User):Boolean{
       return users.add(user)
    }
    fun searchUser(userNameEntered:String):User?{
          var  user:User?=null
        if(!users.none { it.userName.equals(userNameEntered) }) {
            user = users.filter { it.userName.equals(userNameEntered) }.first()
        }
    return user}
}