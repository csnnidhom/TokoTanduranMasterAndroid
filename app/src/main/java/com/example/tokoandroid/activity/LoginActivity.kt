package com.example.tokoandroid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tokoandroid.R
import com.example.tokoandroid.helper.SharedPref

class LoginActivity : AppCompatActivity() {

    lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        s = SharedPref(this)

    }

    fun btn_prosesLogin(view: View) {
        s.setStatusLogin(true)
    }
}
