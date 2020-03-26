package com.pnit.mobile.lab4task3

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var manager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = supportFragmentManager
        pager.adapter = MyAdapter(manager)
        tabs.setupWithViewPager(pager)
    }


    internal class MyAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
        override fun getPageTitle(position: Int): CharSequence? {
            return "FRAGMENT ${position+1}"
        }

        override fun getItem(i: Int): Fragment {
            return when (i) {
                0 -> FirstFragment()
                1 -> SecondFragment()
                2 -> ThirdFragment()
                else -> {
                    FirstFragment()
                }
            }
        }

        override fun getCount(): Int {
            return 3
        }
    }
}
