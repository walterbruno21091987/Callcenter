package com.example.callcenter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import com.example.callcenter.databinding.FragmentChangePasswordBinding
import com.example.callcenter.entities.ColorFavorit
import com.example.callcenter.entities.User
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
  binding.tvOlvidoContraseniaChangePassword.setOnClickListener {
      val userName=arguments?.getString("USERNAME")
      var user: User?=null
      if(userName!=null){
       user=UserRepository.searchUser(userName)}
      binding.tvOlvidoContraseniaChangePassword.setTextColor(R.color.purple_200)
      binding.lyQuestionChangePassword.visibility=View.VISIBLE
      binding.rbPregunta1.text=ColorFavorit.values().random().detalle.toString()
      binding.rbPegunta2.text=ColorFavorit.values().random().detalle.toString()
      binding.rbPegunta3.text=ColorFavorit.values().random().detalle.toString()
      val listBt= listOf<RadioButton>(binding.rbPregunta1,binding.rbPegunta2,binding.rbPegunta3)

      listBt.random().text=user?.favoritColor?.detalle.toString()?:"DESCONOCIDO"
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