package com.example.tokoandroid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tokoandroid.R
import com.example.tokoandroid.app.ApiConfig
import com.example.tokoandroid.model.ResponModel
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
        }else if (edtEmail.text.isEmpty()) {
            edtEmail.error = "Kolom Password Tidak Boleh Kosong"
            edtEmail.requestFocus()
            return
        }else if (edtPhone.text.isEmpty()) {
            edtPhone.error = "Kolom Phone Tidak Boleh Kosong"
            edtPhone.requestFocus()
            return
        }else if (edtPassword.text.isEmpty()) {
            edtPassword.error = "Kolom Email Tidak Boleh Kosong"
            edtPassword.requestFocus()
            return
        }

        ApiConfig.instanceRetrofit.register(edtNama.text.toString(), edtEmail.text.toString(), edtPassword.text.toString(), edtPhone.text.toString()).enqueue(object : Callback<ResponModel>{
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body();

                //handle ketika sukses
                if (respon!!.succes == 1){
                    //berhasil
                    Toast.makeText(this@RegisterActivity, "Selamat Datang  "+respon.user.name, Toast.LENGTH_SHORT).show()
                }else{
                    //gagal
                    Toast.makeText(this@RegisterActivity, "Error"+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                //handle ketika gagal
                Toast.makeText(this@RegisterActivity, "Error"+t.message, Toast.LENGTH_SHORT).show()
            }


        })


    }
}