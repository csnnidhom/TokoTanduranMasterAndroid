package com.example.tokoandroid.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.tokoandroid.MainActivity
import com.example.tokoandroid.R
import com.example.tokoandroid.app.ApiConfig
import com.example.tokoandroid.helper.SharedPref
import com.example.tokoandroid.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var s: SharedPref
    lateinit var btnLogin: Button
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        s = SharedPref(this)

        btnLogin = findViewById(R.id.btn_login)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        progressBar = findViewById(R.id.pb)

        btnLogin.setOnClickListener {
            login()
        }

    }

    fun login() {
        if (edtEmail.text.isEmpty()) {
            edtEmail.error = "Kolom Nama Tidak Boleh Kosong"
            edtEmail.requestFocus()
            return
        } else if (edtPassword.text.isEmpty()) {
            edtPassword.error = "Kolom Password Tidak Boleh Kosong"
            edtPassword.requestFocus()
            return
        }

        progressBar.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.login(edtEmail.text.toString(), edtPassword.text.toString()).enqueue(object : Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body();

                //handle ketika sukses
                progressBar.visibility = View.GONE
                if (respon!!.succes == 1) {
                    //berhasil

                    s.setStatusLogin(true)
                    s.setUser(respon.user)
//                    s.setString(s.nama, respon.user.name)
//                    s.setString(s.email, respon.user.email)
//                    s.setString(s.phone, respon.user.phone)
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@LoginActivity, "Selamat Datang " + respon.user.name, Toast.LENGTH_SHORT).show()
                } else {
                    //gagal
                    Toast.makeText(this@LoginActivity, "Error" + respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                //handle ketika gagal
                progressBar.visibility = View.GONE
                Toast.makeText(this@LoginActivity, "Error" + t.message, Toast.LENGTH_SHORT).show()
            }


        })
    }
}
