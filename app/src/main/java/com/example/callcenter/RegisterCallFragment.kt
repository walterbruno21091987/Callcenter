package com.example.callcenter

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.callcenter.databinding.FragmentRegisterCallBinding
import com.example.callcenter.entities.Call
import com.example.callcenter.entities.Contact
import com.example.callcenter.repository.CallRepository
import com.example.callcenter.repository.ContactRepository

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterCallFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterCallFragment : Fragment() {
    // TODO: Rename and change types of parameters
   lateinit var binding:FragmentRegisterCallBinding
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
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_register_call,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btRegistrerCall.setTextColor(Color.WHITE)
        binding.btRegistrerCall.setOnClickListener {
            registerCall()
        }
    }

    private fun registerCall() {
        binding.btRegistrerCall.setTextColor(Color.BLUE)
        var successCall = false
        var callAgain = false
        val obsevations = binding.etObservaciones.text.toString()
        val phoneContact = arguments?.getLong("PHONE") ?: 0
        val bundle = bundleOf("PHONE" to phoneContact)
        binding.swLlamadaExitosa.setOnCheckedChangeListener { buttonView, isChecked ->
            successCall = binding.swLlamadaExitosa.isChecked
        }
        binding.swVolverALlamar.setOnCheckedChangeListener { buttonView, isChecked ->
            callAgain = binding.swVolverALlamar.isChecked
        }
        if (ContactRepository.searchContact(phoneContact) != null) {
            var add = false
            val call = Call(phoneContact, successCall, callAgain, obsevations)
            add = CallRepository.callAdd(call)
            if (add) {

                Toast.makeText(context, "LLAMADA AGREGADA", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_registerCallFragment_to_userMenu, bundle)
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
         * @return A new instance of fragment RegisterCallFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterCallFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}