package com.example.callcenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.callcenter.databinding.FragmentCallBinding
import com.example.callcenter.exception.SearchContactException
import com.example.callcenter.repository.ContactRepository

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CallFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CallFragment : Fragment() {
   lateinit var binding:FragmentCallBinding
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
      binding= DataBindingUtil.inflate(inflater,R.layout.fragment_call,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       try{ val contact=ContactRepository.callContactRandom()
        binding.tvCodigoPostalCall.text=contact.postalCode.toString()
        binding.tvMailContactCall.text=contact.email.toString()
        binding.tvNameSurnameCall.text=contact.nameAndSurname
        binding.tvProvinciaCall.text=contact.provincia.toString()
        binding.tvContactPhoneCall.text=contact.phone.toString()
        binding.tvLocationCall.text=contact.location}catch (e:SearchContactException){
            Toast.makeText(context,e.message,Toast.LENGTH_LONG).show()
        }
        binding.btSearchNewContact.setOnClickListener {
            try{ val contact=ContactRepository.callContactRandom()
                binding.tvCodigoPostalCall.text=contact.postalCode.toString()
                binding.tvMailContactCall.text=contact.email.toString()
                binding.tvNameSurnameCall.text=contact.nameAndSurname
                binding.tvProvinciaCall.text=contact.provincia.toString()
                binding.tvContactPhoneCall.text=contact.phone.toString()
                binding.tvLocationCall.text=contact.location}catch (e:SearchContactException){
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
         * @return A new instance of fragment CallFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CallFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}