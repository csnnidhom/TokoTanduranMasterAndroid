package com.example.tokoandroid.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.tokoandroid.R
import com.example.tokoandroid.helper.SharedPref

class MasukActivity : AppCompatActivity() {

    lateinit var s: SharedPref
    lateinit var btnProsesLogin: Button
    lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masuk)

        btnProsesLogin = findViewById(R.id.btn_prosesLogin);
        btnRegister = findViewById(R.id.btn_register);

        s = SharedPref(this)

        mainButton();
    }

    private fun mainButton(){
        btnProsesLogin.setOnClickListener{
            s.setStatusLogin(true);
        }

        btnRegister.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java));
        }
    }

}
