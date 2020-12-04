package com.example.tokoandroid.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.tokoandroid.R
import com.example.tokoandroid.adapter.AdapterProduk
import com.example.tokoandroid.model.Produk
import com.inyongtisto.tokoonline.adapter.AdapterSlider


class HomeFragment : Fragment() {

    lateinit var vpSlider: ViewPager
    lateinit var rvProduk: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View=inflater.inflate(R.layout.fragment_home, container, false)

        vpSlider = view.findViewById(R.id.vp_slider)
        rvProduk = view.findViewById(R.id.rv_produk)

        val arrSlider=ArrayList<Int>()
        arrSlider.add(R.drawable.slider3)
        arrSlider.add(R.drawable.slider2)
        arrSlider.add(R.drawable.slider1)

        val adapterSlider=AdapterSlider(arrSlider, activity)
        vpSlider.adapter=adapterSlider

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        rvProduk.adapter = AdapterProduk(arrProduk)
        rvProduk.layoutManager = layoutManager

        return view
    }

    val arrProduk: ArrayList<Produk>get(){
        val arr=ArrayList<Produk>()
        val p1=Produk()
        p1.nama="bunga 1"
        p1.harga="Rp 100.00"
        p1.gambar=R.drawable.bunga1

        val p2=Produk()
        p2.nama="bunga 2"
        p2.harga="Rp 200.00"
        p2.gambar=R.drawable.bunga2

        val p3=Produk()
        p3.nama="bunga 3"
        p3.harga="Rp 300.00"
        p3.gambar=R.drawable.bunga3

        arr.add(p1)
        arr.add(p2)
        arr.add(p3)

        return arr
    }

}