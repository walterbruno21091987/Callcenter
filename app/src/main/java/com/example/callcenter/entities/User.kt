package com.example.callcenter.entities

class User(val userName:String,var password:String, val name:String,val surname:String,var favoritColor: ColorFavorit) {

    fun changePassword(newPassword:String){
    password=newPassword
}
    fun login(passwordentered:String):Boolean{
        return passwordentered.equals(password)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (userName != other.userName) return false

        return true
    }

    override fun hashCode(): Int {
        return userName.hashCode()
    }

    companion object{
    fun validatePassword(passwordEnter: String):Boolean{
        var result=false
        var length=false
        var mayuscula=false
        var minuscula=false
        if(passwordEnter.length>=8&&passwordEnter.length<=16){
        length=true
        }
   if  (!passwordEnter.none { it>='A'&&it<'[' }){
       mayuscula=true
   }
   if(!passwordEnter.none { it>='a'&&it<'{' })
       minuscula=true
    if(length&&mayuscula&&minuscula){
        result=true
    }
        return result
    }
}
}
