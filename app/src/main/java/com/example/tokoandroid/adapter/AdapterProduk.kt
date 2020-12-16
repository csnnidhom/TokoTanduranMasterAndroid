package com.example.tokoandroid.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tokoandroid.R
import com.example.tokoandroid.activity.DetailProdukActivity
import com.example.tokoandroid.helper.Helper
import com.example.tokoandroid.model.Produk
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class AdapterProduk(var activity: Activity, var data:ArrayList<Produk>): RecyclerView.Adapter<AdapterProduk.Holder>() {

    class Holder(view: View):RecyclerView.ViewHolder(view){
        val tvNama = view.findViewById<TextView>(R.id.tv_nama)
        val tvHarga = view.findViewById<TextView>(R.id.tv_harga)
        val imgProduk = view.findViewById<ImageView>(R.id.img_produk)
        val layout = view.findViewById<CardView>(R.id.layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View=LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvNama.text = data[position].name
        holder.tvHarga.text = Helper().gantiRupiah(data[position].harga)
//        holder.imgProduk.setImageResource(data[position].image)

        val image = "http://192.168.1.64/AdminTokoTanduranMasterWebsite/public/storage/produk/"+data[position].image
        Picasso.get()
                .load(image)
                .placeholder(R.drawable.bunga1)
                .error(R.drawable.bunga1)
                .into(holder.imgProduk)

        holder.layout.setOnClickListener{
                val acticity = Intent(activity, DetailProdukActivity::class.java)
                val str = Gson().toJson(data[position], Produk::class.java)
                acticity.putExtra("extra", str)
                activity.startActivity(acticity)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}