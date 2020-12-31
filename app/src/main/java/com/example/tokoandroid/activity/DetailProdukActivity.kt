package com.example.tokoandroid.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.tokoandroid.R
import com.example.tokoandroid.helper.Helper
import com.example.tokoandroid.model.Produk
import com.example.tokoandroid.room.MyDatabase
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.toolbar_custom.*

class DetailProdukActivity : AppCompatActivity() {

    lateinit var myDb :MyDatabase
    lateinit var produk: Produk
    lateinit var tvNama: TextView
    lateinit var tvHarga: TextView
    lateinit var imgImage: ImageView
    lateinit var tvDeskripsi: TextView
    lateinit var btnKeranjang: RelativeLayout
    lateinit var btnFavorit: RelativeLayout
    lateinit var nameToolbar: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)

        tvNama = findViewById(R.id.tv_nama)
        tvHarga = findViewById(R.id.tv_harga)
        tvDeskripsi = findViewById(R.id.tv_deskripsi)
        imgImage = findViewById(R.id.iv_image)
        nameToolbar = findViewById(R.id.toolbar)
        btnKeranjang = findViewById(R.id.btn_keranjang)
        btnFavorit = findViewById(R.id.btn_favorit)
        myDb= MyDatabase.getInstance(this)!!

        getInfo()
        mainButton()
        cekKeranjang()
    }

    private fun mainButton(){
        btnKeranjang.setOnClickListener{
            insert()
        }

        btnFavorit.setOnClickListener{

            val listData = myDb.daoKeranjang().getAll() // get All data
            for(note :Produk in listData){
                println("-----------------------")
                println(note.name)
                println(note.harga)
            }
        }
    }

    private fun insert(){
        CompositeDisposable().add(Observable.fromCallable { myDb.daoKeranjang().insert(produk) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                cekKeranjang()
                Log.d("respons", "data inserted")
            })
    }

    private fun cekKeranjang(){
        val dataKeranjang = myDb.daoKeranjang().getAll()

        if (dataKeranjang.isNotEmpty()){
            div_angka.visibility = View.VISIBLE
            tv_angka.text = dataKeranjang.size.toString()
        }else{
            div_angka.visibility = View.GONE
        }
    }

    private fun getInfo(){
        val data = intent.getStringExtra("extra")
        produk = Gson().fromJson<Produk>(data, Produk::class.java)

        // set value
        tvNama.text = produk.name
        tvHarga.text = Helper().gantiRupiah(produk.harga)
        tvDeskripsi.text = produk.deskripsi

        val img = "http://192.168.1.64/AdminTokoTanduranMasterWebsite/public/storage/produk/"+produk.image
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