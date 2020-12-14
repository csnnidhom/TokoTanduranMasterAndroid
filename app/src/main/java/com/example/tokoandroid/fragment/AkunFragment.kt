package com.example.tokoandroid.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.tokoandroid.R
import com.example.tokoandroid.helper.SharedPref

class AkunFragment : Fragment() {

    lateinit var s: SharedPref
    lateinit var btnLogout: TextView
    lateinit var tvNama: TextView
    lateinit var tvEmail: TextView
    lateinit var tvPhone: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_account, container, false)
        btnLogout = view.findViewById(R.id.btn_logout)
        init(view)

        s = SharedPref(requireActivity())

        btnLogout.setOnClickListener{
            s.setStatusLogin(false)
        }

        setData()
        return view
    }

    fun setData(){
        tvNama.text = s.getString(s.nama)
        tvEmail.text = s.getString(s.email)
        tvPhone.text = s.getString(s.phone)
    }

    private fun init(view: View) {
        btnLogout = view.findViewById(R.id.btn_logout)
        tvNama = view.findViewById(R.id.tv_nama)
        tvEmail = view.findViewById(R.id.tv_email)
        tvPhone = view.findViewById(R.id.tv_phone)
    }

}