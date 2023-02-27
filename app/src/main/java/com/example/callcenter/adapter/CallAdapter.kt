package com.example.callcenter.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.callcenter.R
import com.example.callcenter.databinding.ItemCallBinding
import com.example.callcenter.entities.Call

class CallAdapter(val callList: List<Call>):RecyclerView.Adapter<CallViewHolder>(){
    lateinit var binding: ItemCallBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallViewHolder {
        binding= DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_call,parent,false)
    return CallViewHolder(binding.root)
    }


    override fun onBindViewHolder(holder: CallViewHolder, position: Int) {
     val call=callList[position]
        render(call)
    }

    override fun getItemCount(): Int {
        return callList.size
    }
    fun render(call: Call){
        binding.etContactPhoneItem.setText(call.contact.toString())
        if(!call.callAgain){
            binding.etLlamarNuevamenteItem.setText("NO LLAMAR")
            binding.etLlamarNuevamenteItem.setTextColor(Color.RED)
        }
        if(call.successful){
            binding.etLlamadaExitosaItem.setText("LLAMADA EXITOSA")
            binding.etLlamadaExitosaItem.setTextColor(Color.GREEN)
        }
        binding.etObservacionesCall.setText(call.observations)
    }

}