package com.example.callcenter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.callcenter.databinding.FragmentChangePasswordBinding
import com.example.callcenter.entities.ColorFavorit
import com.example.callcenter.entities.User
import com.example.callcenter.exception.PasswordInvalidException
import com.example.callcenter.repository.UserRepository

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChangePassword.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChangePassword : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding:FragmentChangePasswordBinding

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
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_change_password,container,false)
        return binding.root
    }

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userName=arguments?.getString("USERNAME")
        var user: User?=null
        if(userName!=null){
            user=UserRepository.searchUser(userName)}
        binding.btChangePassword.setOnClickListener{
         try { val password=binding.etChangePasswordNewPassword.text.toString()
            val confirmPassword=binding.etChangePasswordConfirmPassword.text.toString()
            if(User.validatePassword(password)){
                if(confirmPassword.equals(password)) {
                    user?.password = binding.etChangePasswordNewPassword.text.toString()
                    Toast.makeText(context, "CONTRASEÑA CAMBIADA CORRECTAMENTE", Toast.LENGTH_LONG)
                        .show()
                    findNavController().navigate(R.id.action_changePassword_to_loginUser)
                }else{
                    Toast.makeText(context, "LA CONTRASEÑA DEBE SER IGUAL A LA CONFIRMACION DE LA MISMA", Toast.LENGTH_LONG)
                        .show()

                }
            }
            else {
                throw PasswordInvalidException("Contraseña invalida, debe contener entre 8 y 16  digitos y almenos una mayuscula y una minuscula ")
            }}catch (e:PasswordInvalidException){
             Toast.makeText(context,e.message,Toast.LENGTH_LONG).show()
            }
        }
  binding.tvOlvidoContraseniaChangePassword.setOnClickListener {

      binding.tvOlvidoContraseniaChangePassword.setTextColor(R.color.purple_200)
      binding.lyQuestionChangePassword.visibility=View.VISIBLE
      binding.rbPregunta1.text=ColorFavorit.values().random().detalle.toString()
      binding.rbPegunta2.text=ColorFavorit.values().random().detalle.toString()
      binding.rbPegunta3.text=ColorFavorit.values().random().detalle.toString()
      val listBt= listOf<RadioButton>(binding.rbPregunta1,binding.rbPegunta2,binding.rbPegunta3)

      listBt.random().text=user?.favoritColor?.detalle?:"DESCONOCIDO"
      binding.btResponderChangePassword.setOnClickListener {
          if(listBt.filter{ it.text.equals(user?.favoritColor?.detalle?:"DESCONOCIDO" )}.first().isChecked){
              user?.password="Abcd1234"
              Toast.makeText(context,"SU NIEVA CONTRASEÑA ES ${user?.password} POR FAVOR CAMBIELA",Toast.LENGTH_LONG).show()

              binding.lyQuestionChangePassword.visibility=View.GONE
          }
          else{
              Toast.makeText(context,"RESPUESTA INCORRECTA",Toast.LENGTH_LONG).show()
              binding.lyQuestionChangePassword.visibility=View.GONE
              findNavController().navigate(R.id.action_changePassword_to_loginUser)
          }
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
         * @return A new instance of fragment ChangePassword.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChangePassword().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}