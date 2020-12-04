package com.example.tokoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.tokoandroid.fragment.AkunFragment
import com.example.tokoandroid.fragment.HomeFragment
import com.example.tokoandroid.fragment.KeranjangFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val fragmentHome: Fragment=HomeFragment();
    private val fragmentKeranjang: Fragment=KeranjangFragment();
    private val fragmentAkun: Fragment= AkunFragment();
    private val fm: FragmentManager=supportFragmentManager
    private var active: Fragment=fragmentHome

    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpBottomNav()
    }

    fun setUpBottomNav(){
        fm.beginTransaction().add(R.id.container, fragmentHome). show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.container, fragmentKeranjang). hide(fragmentKeranjang).commit()
        fm.beginTransaction().add(R.id.container, fragmentAkun). hide(fragmentAkun).commit()

        bottomNavigationView=findViewById(R.id.nav_view)
        menu=bottomNavigationView.menu
        menuItem=menu.getItem(0)
        menuItem.isChecked=true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when(item.itemId){
                R.id.navigation_home->{
                    calledFragment(0, fragmentHome)
                }
                R.id.navigation_keranjang->{
                    calledFragment(1, fragmentKeranjang)
                }
                R.id.navigation_akun->{
                    calledFragment(2, fragmentAkun)
                }
            }

            false }
    }

    fun calledFragment(int: Int, fragment: Fragment){
        menuItem=menu.getItem(int)
        menuItem.isChecked=true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active=fragment
    }
}