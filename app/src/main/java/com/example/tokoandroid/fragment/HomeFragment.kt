package com.example.tokoandroid.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.tokoandroid.MainActivity
import com.example.tokoandroid.R
import com.example.tokoandroid.adapter.AdapterProduk
import com.example.tokoandroid.app.ApiConfig
import com.example.tokoandroid.model.Produk
import com.example.tokoandroid.model.ResponModel
import com.inyongtisto.tokoonline.adapter.AdapterSlider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    lateinit var vpSlider: ViewPager
    lateinit var rvProduk: RecyclerView
    lateinit var rvProdukTerlaris: RecyclerView
    lateinit var rvProdukUnggulan: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View=inflater.inflate(R.layout.fragment_home, container, false)

        init(view)
        getProduk()

        return view
    }

    fun displayProduk(){
        val arrSlider=ArrayList<Int>()
        arrSlider.add(R.drawable.slider3)
        arrSlider.add(R.drawable.slider2)
        arrSlider.add(R.drawable.slider1)

        val adapterSlider=AdapterSlider(arrSlider, activity)
        vpSlider.adapter=adapterSlider

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager2= LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager3= LinearLayoutManager(activity)
        layoutManager3.orientation = LinearLayoutManager.HORIZONTAL

        rvProduk.adapter = AdapterProduk(listProduk)
        rvProduk.layoutManager = layoutManager

        rvProdukTerlaris.adapter = AdapterProduk(listProduk)
        rvProdukTerlaris.layoutManager = layoutManager2

        rvProdukUnggulan.adapter = AdapterProduk(listProduk)
        rvProdukUnggulan.layoutManager = layoutManager3
    }

    private var listProduk: ArrayList<Produk> = ArrayList()
    fun getProduk(){
        ApiConfig.instanceRetrofit.getProduk().enqueue(object : Callback<ResponModel> {
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val res = response.body()!!
                if (res.succes == 1){
                    listProduk = res.produks
                    displayProduk()
                }
            }

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {

            }
        })

    }

    fun init(view: View){
        vpSlider = view.findViewById(R.id.vp_slider)
        rvProduk = view.findViewById(R.id.rv_produk)
        rvProdukTerlaris = view.findViewById(R.id.rv_produkTerlaris)
        rvProdukUnggulan = view.findViewById(R.id.rv_produkUnggulan)
    }

/*    val arrProduk: ArrayList<Produk>get(){
        val arr=ArrayList<Produk>()
        val p1=Produk()
        p1.nama="bunga 1"
        p1.harga="Rp 100.000"
        p1.gambar=R.drawable.bunga1

        val p2=Produk()
        p2.nama="bunga 2"
        p2.harga="Rp 200.000"
        p2.gambar=R.drawable.bunga2

        val p3=Produk()
        p3.nama="bunga 3"
        p3.harga="Rp 300.000"
        p3.gambar=R.drawable.bunga3

        arr.add(p1)
        arr.add(p2)
        arr.add(p3)

        return arr
    }

    val arrProdukTerlaris: ArrayList<Produk>get(){
        val arr=ArrayList<Produk>()
        val p1=Produk()
        p1.nama="bunga 4"
        p1.harga="Rp 400.000"
        p1.gambar=R.drawable.bunga4

        val p2=Produk()
        p2.nama="bunga 5"
        p2.harga="Rp 500.000"
        p2.gambar=R.drawable.bunga5

        val p3=Produk()
        p3.nama="bunga 6"
        p3.harga="Rp 600.000"
        p3.gambar=R.drawable.bunga6

        arr.add(p1)
        arr.add(p2)
        arr.add(p3)

        return arr
    }

    val arrProdukUnggulan: ArrayList<Produk>get(){
        val arr=ArrayList<Produk>()
        val p1=Produk()
        p1.nama="bunga 7"
        p1.harga="Rp 700.000"
        p1.gambar=R.drawable.bunga7

        val p2=Produk()
        p2.nama="bunga 5"
        p2.harga="Rp 800.000"
        p2.gambar=R.drawable.bunga8

        val p3=Produk()
        p3.nama="bunga 6"
        p3.harga="Rp 900.000"
        p3.gambar=R.drawable.bunga9

        arr.add(p1)
        arr.add(p2)
        arr.add(p3)

        return arr
    }

 */

}