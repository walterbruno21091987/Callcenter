package com.example.callcenter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.callcenter.databinding.FragmentRegisterUserBinding
import com.example.callcenter.entities.ColorFavorit
import com.example.callcenter.entities.User
import com.example.callcenter.exception.ColorNonExistenException
import com.example.callcenter.exception.NameUserExistingException
import com.example.callcenter.exception.PasswordInvalidException
import com.example.callcenter.repository.UserRepository

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class registerUser : Fragment() {
    lateinit var binding:FragmentRegisterUserBinding
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
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_register_user,container,false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imBack.setOnClickListener {
            findNavController().navigate(R.id.action_registerUser_to_loginUser)
        }
        val colors:Array<String> =resources.getStringArray(R.array.colors)
        val adapter:ArrayAdapter<String> =ArrayAdapter<String>(view.context,android.R.layout.simple_dropdown_item_1line,colors)
        binding.etFavoritColor.setAdapter(adapter)

        binding.btRegistrar.setOnClickListener {
          try {
              try {
                  try {
                      val username = binding.etUserRegister.text.toString()
                      if (UserRepository.searchUser(username) != null) throw NameUserExistingException(
                          "NOMBRE DE USUARIO YA EXISTENTE"
                      )
                      val name = binding.etNameRegister.text.toString()
                      val password = binding.etPasswordRegister.text.toString()
                      val favoritColor = binding.etFavoritColor.text.toString()
                      if (!colors.contains(favoritColor)) {
                          throw ColorNonExistenException("COLOR INEXISTENTE")
                      }
                      val surname = binding.etSurnameRegister.text.toString()
                      if (User.validatePassword(password)) {
                          val user = User(
                              username,
                              password,
                              name,
                              surname,
                              ColorFavorit.valueOf(favoritColor)
                          )
                          Toast.makeText(context, "USUARIO REGISTRADO", Toast.LENGTH_LONG).show()
                          UserRepository.addUser(user)
                          findNavController().navigate(R.id.action_registerUser_to_loginUser)
                      } else {
                          throw PasswordInvalidException("Contraseña invalida, debe contener entre 8 y 16  digitos y almenos una mayuscula y una minuscula ")
                      }
                  } catch (e: PasswordInvalidException) {
                      Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                  }
              } catch (e: NameUserExistingException) {
                  Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
              }
          }catch (e:ColorNonExistenException){
              Toast.makeText(context,e.message,Toast.LENGTH_LONG).show()
          }}
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment registerUser.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            registerUser().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}