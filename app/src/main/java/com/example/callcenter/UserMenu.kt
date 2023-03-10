package com.example.callcenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.callcenter.databinding.FragmentUserMenuBinding
import com.example.callcenter.entities.User
import com.example.callcenter.repository.UserRepository

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [UserMenu.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserMenu : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding:FragmentUserMenuBinding
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
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_user_menu,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userName=arguments?.getString("USERNAME")
        var user: User?=null
        if(userName!=null){
            user= UserRepository.searchUser(userName)}
        val bundle= bundleOf("USERNAME" to userName )
        binding.btSalir.setOnClickListener {
            findNavController().navigate(R.id.action_userMenu_to_loginUser)
        }
        binding.btCambiarContrasenia.setOnClickListener {
            findNavController().navigate(R.id.action_userMenu_to_changePassword,bundle)
        }
        binding.btRegistrarNuevoContacto.setOnClickListener {
            findNavController().navigate(R.id.action_userMenu_to_registerContact)
        }
        binding.btRealizarLlamada.setOnClickListener {
            findNavController().navigate(R.id.action_userMenu_to_callFragment)
        }
        binding.btVerLlamadasRealizadas.setOnClickListener {
            findNavController().navigate(R.id.action_userMenu_to_listCallFragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserMenu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserMenu().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}