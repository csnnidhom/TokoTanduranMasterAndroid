package com.example.tokoandroid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.tokoandroid.R
import com.example.tokoandroid.app.ApiConfig
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    lateinit var btnRegister: Button
    lateinit var edtNama: EditText
    lateinit var edtPhone: EditText
    lateinit var edtPassword: EditText
    lateinit var edtEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister = findViewById(R.id.btn_register);
        edtNama = findViewById(R.id.edt_nama);
        edtPhone = findViewById(R.id.edt_phone);
        edtPassword = findViewById(R.id.edt_password);
        edtEmail = findViewById(R.id.edt_email);

        btnRegister.setOnClickListener{
            register()
        }
    }

    fun register(){
        if (edtNama.text.isEmpty()){
            edtNama.error = "Kolom Nama Tidak Boleh Kosong"
            edtNama.requestFocus()
            return
        }else if (edtPassword.text.isEmpty()) {
            edtPassword.error = "Kolom Password Tidak Boleh Kosong"
            edtPassword.requestFocus()
            return
        }else if (edtPhone.text.isEmpty()) {
            edtPhone.error = "Kolom Phone Tidak Boleh Kosong"
            edtPhone.requestFocus()
            return
        }else if (edtEmail.text.isEmpty()) {
            edtEmail.error = "Kolom Email Tidak Boleh Kosong"
            edtEmail.requestFocus()
            return
        }

        ApiConfig.instanceRetrofit.register(edtNama.text.toString(), edtEmail.text.toString(), edtPassword.text.toString(), edtPhone.text.toString()).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                //handle ketika sukses
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //handle ketika gagal
            }

        })


    }
}