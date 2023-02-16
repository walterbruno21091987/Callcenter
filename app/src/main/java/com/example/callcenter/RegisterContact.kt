package com.example.callcenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.callcenter.databinding.FragmentRegisterContactBinding
import com.example.callcenter.entities.Contact
import com.example.callcenter.entities.Provincia
import com.example.callcenter.exception.ColorNonExistenException
import com.example.callcenter.exception.EmailInvalidException
import com.example.callcenter.exception.PhoneExistingException
import com.example.callcenter.exception.ProvinciaNonExistenException
import com.example.callcenter.repository.ContactRepository


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RegisterContact : Fragment() {
    lateinit var binding:FragmentRegisterContactBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_register_contact,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val provincias:Array<String> =resources.getStringArray(R.array.provincia)
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(view.context,android.R.layout.simple_dropdown_item_1line,provincias)
        binding.etProvinciaContactRegister.setAdapter(adapter)
        binding.btRegistrar.setOnClickListener {
          try{ try{ try{val nameSurname=binding.etNameSurnameContactRegister.text.toString()
            val celular=binding.etPhoneContactRegister.text.toString().toLong()
            val email=binding.etEmailContactRegister.text.toString()
            val direccion=binding.etDireccionContactRegister.text.toString()
            val codPostal=binding.etCodPostalContact.text.toString().toInt()
            val localidad=binding.etLocalidadContactRegister.text.toString()
            val provincia=binding.etProvinciaContactRegister.text.toString()
            var add=false
            if(!Contact.validateEmail(email)){
                throw EmailInvalidException("DEBE INGRESAR UN MAIL VALIDO")
            }
            if (!provincias.contains(provincia)) {
                throw ProvinciaNonExistenException("PROVINCIA INEXISTENTE")
            }
            if(ContactRepository.searchContact(celular)!=null){
                throw PhoneExistingException("TELEFONO YA VINCULADO A OTRO CONTACTO")
            }
            add=ContactRepository.addUser(Contact(nameSurname,celular,email,direccion,codPostal,localidad,Provincia.valueOf(provincia)))
             if(add){
                 Toast.makeText(context,"CONTACTO AGREGADO",Toast.LENGTH_LONG).show()
                 findNavController().navigate(R.id.action_registerContact_to_userMenu)
             }
        }catch (e:EmailInvalidException){
        Toast.makeText(context,e.message,Toast.LENGTH_LONG).show()}
        }catch (e:PhoneExistingException){
               Toast.makeText(context,e.message,Toast.LENGTH_LONG).show()}
        }catch (e:ProvinciaNonExistenException){
              Toast.makeText(context,e.message,Toast.LENGTH_LONG).show()}
        }
        binding.imBack.setOnClickListener {
            findNavController().navigate(R.id.action_registerContact_to_userMenu)
        }
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            RegisterContact().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}