package com.example.callcenter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.callcenter.databinding.FragmentLoginUserBinding
import com.example.callcenter.exception.UsuarioInexistenteExeptions
import com.example.callcenter.repository.UserRepository

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginUser : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding:FragmentLoginUserBinding
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

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_login_user,container,false)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btIngresar.setOnClickListener {
         try{   val userName=binding.etUser.text.toString()
            val password=binding.etPasword.text.toString()
            val user=UserRepository.searchUser(userName)
          if(user!=null){
            if(user.login(password)){
                val bundle= bundleOf("USERNAME" to userName )
                findNavController().navigate(R.id.action_loginUser_to_userMenu,bundle)
            }
            else { throw UsuarioInexistenteExeptions("USUARIO O CONTRASEÑA INCORRECTA")

            }
        }
        else{
              throw UsuarioInexistenteExeptions("USUARIO O CONTRASEÑA INCORRECTA")
        }}catch (e:UsuarioInexistenteExeptions){
        Toast.makeText(context,e.message,Toast.LENGTH_LONG).show()}
        }
        binding.tvRegistrarse.setOnClickListener{
            binding.tvRegistrarse.setTextColor(R.color.purple_200)
            findNavController().navigate(R.id.action_loginUser_to_registerUser)
        }
        binding.tvOlvidoContrasenia.setOnClickListener {
            val userName=binding.etUser.text.toString()
            val bundle= bundleOf("USERNAME" to userName )

            binding.tvOlvidoContrasenia.setTextColor(R.color.purple_200)
            try{if(UserRepository.searchUser(userName)!=null){
            findNavController().navigate(R.id.action_loginUser_to_changePassword,bundle)}
            else {
                throw UsuarioInexistenteExeptions("USUARIO INEXISTENTE")
            }
            }catch (e:UsuarioInexistenteExeptions){
                Toast.makeText(context,e.message,Toast.LENGTH_LONG).show()
            }
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginUser.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginUser().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}