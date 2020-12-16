package com.example.tokoandroid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.tokoandroid.R
import com.example.tokoandroid.helper.Helper
import com.example.tokoandroid.model.Produk
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class DetailProdukActivity : AppCompatActivity() {

    lateinit var tvNama: TextView
    lateinit var tvHarga: TextView
    lateinit var imgImage: ImageView
    lateinit var tvDeskripsi: TextView
    lateinit var nameToolbar: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)

        tvNama = findViewById(R.id.tv_nama)
        tvHarga = findViewById(R.id.tv_harga)
        tvDeskripsi = findViewById(R.id.tv_deskripsi)
        imgImage = findViewById(R.id.iv_image)
        nameToolbar = findViewById(R.id.toolbar)

        getInfo()
    }

    fun getInfo(){
        val data = intent.getStringExtra("extra")
        val produk = Gson().fromJson<Produk>(data, Produk::class.java)

        // set value
        tvNama.text = produk.name
        tvHarga.text = Helper().gantiRupiah(produk.harga)
        tvDeskripsi.text = produk.deskripsi

        val img = "http://192.168.1.64/AdminTanduran/public/storage/produk/"+produk.image
        Picasso.get()
                .load(img)
                .placeholder(R.drawable.bunga1)
                .error(R.drawable.bunga1)
                .resize(400,400)
                .into(imgImage)

        // set Toolbar
        setSupportActionBar(nameToolbar)
        supportActionBar!!.title = produk.name
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}