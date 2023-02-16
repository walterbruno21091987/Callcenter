package com.example.callcenter.repository

import com.example.callcenter.entities.ColorFavorit
import com.example.callcenter.entities.Contact
import com.example.callcenter.entities.Provincia
import com.example.callcenter.entities.User
import com.example.callcenter.exception.SearchContactException

object ContactRepository {
    private var contacts:MutableSet<Contact> = mutableSetOf()
    init{
        contacts.add(Contact("Walter bruno",1524560234,"ratawalter@hotmail.com","El quiya 630",1778,"La matanza",Provincia.BUENOS_AIRES, ))
    }
    fun addUser(contact: Contact):Boolean{
        return contacts.add(contact)
    }
    fun searchContact(phoneEntered:Long): Contact?{
        var  contact :Contact?=null
        if(!contacts.none { it.phone.equals(phoneEntered) }) {
            contact = contacts.filter { it.phone.equals(phoneEntered) }.first()
        }
        return contact}
    fun callContactRandom(): Contact {
        var contact: Contact
        var searchAttempts=0
        do{ contact=contacts.random()
        searchAttempts++}while ((contact.isClient || !contact.callAgain || !CoverageArea.getArea(contact.postalCode))&&searchAttempts<40)
  if(searchAttempts>=40){
      throw SearchContactException("NO SE AH ENCONTRADO UN CONTACTO CANDIDATO A LLAMAR")
  }
    return contact
    }
}